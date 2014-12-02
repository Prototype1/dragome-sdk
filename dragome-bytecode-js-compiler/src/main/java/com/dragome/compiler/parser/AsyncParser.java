package com.dragome.compiler.parser;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.bcel.classfile.AnnotationEntry;
import org.apache.bcel.classfile.Annotations;
import org.apache.bcel.classfile.Attribute;
import org.apache.bcel.classfile.AttributeReader;
import org.apache.bcel.classfile.ClassParser;
import org.apache.bcel.classfile.ConstantClass;
import org.apache.bcel.classfile.ConstantPool;
import org.apache.bcel.classfile.DescendingVisitor;
import org.apache.bcel.classfile.ElementValuePair;
import org.apache.bcel.classfile.EmptyVisitor;
import org.apache.bcel.classfile.Field;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.classfile.Method;
import org.apache.bcel.generic.ObjectType;
import org.apache.bcel.generic.Type;
import org.apache.commons.io.IOUtils;

import com.dragome.commons.compiler.annotations.ServerOnly;
import com.dragome.compiler.DragomeJsCompiler;
import com.dragome.compiler.Project;
import com.dragome.compiler.annotations.AnnotationReader;
import com.dragome.compiler.ast.ASTNode;
import com.dragome.compiler.ast.Block;
import com.dragome.compiler.ast.MethodBinding;
import com.dragome.compiler.ast.MethodDeclaration;
import com.dragome.compiler.ast.ReturnStatement;
import com.dragome.compiler.ast.TypeDeclaration;
import com.dragome.compiler.ast.VariableDeclaration;
import com.dragome.compiler.exceptions.FatalParseException;
import com.dragome.compiler.exceptions.ServerInClientCode;
import com.dragome.compiler.exceptions.UnhandledCompilerProblemException;
import com.dragome.compiler.generators.DragomeJavaScriptGenerator;
import com.dragome.compiler.invokedynamic.InvokeDynamicBackporter;
import com.dragome.compiler.task.Parsable;
import com.dragome.compiler.type.Signature;
import com.dragome.compiler.units.ClassUnit;
import com.dragome.compiler.utils.Log;
import com.dragome.compiler.utils.Utils;

public class AsyncParser implements Parsable<ClassUnit>
{
	public final static String DONTPARSE= ServerOnly.class.getName();

	private final InvokeDynamicBackporter lambdaUsageBackporter;

	private JavaClass jc;
	private ClassUnit fileUnit;

	public AsyncParser()
	{
		lambdaUsageBackporter= new InvokeDynamicBackporter();
	}

	public static String getResourcePath(String name)
	{
		name= name.replace('.', '/') + ".class";
		java.net.URL url= Parser.class.getClassLoader().getResource(name);
		if (url == null)
			throw new RuntimeException("Resource not found: " + name);
		return url.getPath();
	}

	@Override
	public TypeDeclaration parse() throws ServerInClientCode, FatalParseException
	{
		DescendingVisitor classWalker= new DescendingVisitor(jc, new EmptyVisitor()
		{
			public void visitConstantClass(ConstantClass obj)
			{
				ConstantPool cp= jc.getConstantPool();
				String bytes= obj.getBytes(cp);
				fileUnit.addDependency(bytes.replace("/", "."));
			}
		});
		classWalker.visit();

		org.apache.bcel.classfile.Method[] bcelMethods= jc.getMethods();

		ObjectType type= new ObjectType(jc.getClassName());

		Map<String, String> annotationsValues= getAnnotationsValues(collectEntries(jc.getAttributes()));
		TypeDeclaration typeDecl= new TypeDeclaration(type, jc.getAccessFlags(), annotationsValues);

		if (annotationsValues.containsKey(DONTPARSE))
			throw new ServerInClientCode("Can't parse " + jc.getClassName() + ". " + DONTPARSE + " found");

		Project.singleton.addTypeAnnotations(typeDecl);

		fileUnit.isInterface= Modifier.isInterface(typeDecl.getAccess());
		fileUnit.isAbstract= Modifier.isAbstract(typeDecl.getAccess());

		fileUnit.setAnnotations(annotationsValues);

		if (!type.getClassName().equals("java.lang.Object"))
		{

			ObjectType superType= new ObjectType(jc.getSuperclassName());
			typeDecl.setSuperType(superType);
			ClassUnit superUnit= Project.getSingleton().getOrCreateClassUnit(superType.getClassName());
			fileUnit.setSuperUnit(superUnit);

			String[] interfaceNames= jc.getInterfaceNames();
			for (int i= 0; i < interfaceNames.length; i++)
			{
				ObjectType interfaceType= new ObjectType(interfaceNames[i]);
				ClassUnit interfaceUnit= Project.getSingleton().getOrCreateClassUnit(interfaceType.getClassName());
				fileUnit.addInterface(interfaceUnit);
			}
		}

		Field[] fields= jc.getFields();
		for (int i= 0; i < fields.length; i++)
		{
			Field field= fields[i];
			VariableDeclaration variableDecl= new VariableDeclaration(VariableDeclaration.NON_LOCAL);
			variableDecl.setName(field.getName());
			variableDecl.setModifiers(field.getModifiers());
			variableDecl.setType(field.getType());

			typeDecl.addField(variableDecl);
		}

		for (int i= 0; i < bcelMethods.length; i++)
		{
			Method method= bcelMethods[i];

			Map<String, String> methodAnnotationsValues= getAnnotationsValues(collectEntries(method.getAttributes()));

			if (methodAnnotationsValues.containsKey(DONTPARSE))
			{
				Log.getLogger().warn("Can't parse: " + method.getName() + "." + DONTPARSE + " found");
				continue;
			}

			MethodBinding binding= MethodBinding.lookup(jc.getClassName(), method.getName(), method.getSignature());

			String genericSignature= method.getGenericSignature();
			if (genericSignature != null && !genericSignature.equals(method.getSignature()))
			{
				Signature signature= Project.getSingleton().getSignature(binding.toString()).relative();
				String normalizedSignature= DragomeJavaScriptGenerator.normalizeExpression(signature);
				String normalizedClassname= DragomeJavaScriptGenerator.normalizeExpression(type.getClassName());
				Project.getSingleton().addGenericSignature(normalizedClassname + "|" + normalizedSignature + "|" + genericSignature);
				//		System.out.println(genericSignature);
			}

			if (DragomeJsCompiler.compiler.getSingleEntryPoint() != null)
			{
				Signature signature= Project.getSingleton().getSignature(binding.toString());
				String singleSignature= DragomeJsCompiler.compiler.getSingleEntryPoint();
				if (!signature.toString().equals(singleSignature))
					continue;
			}

			MethodDeclaration methodDecl= new MethodDeclaration(binding, method.getAccessFlags(), method.getCode(), methodAnnotationsValues);
			typeDecl.addMethod(methodDecl);

			parseMethod(typeDecl, methodDecl, method);
		}

		return typeDecl;
	}

	@Override
	public void prepareParsing(ClassUnit t) throws Exception
	{
		fileUnit= t;
		fileUnit.annotations= null;

		AttributeReader r= new AnnotationReader(fileUnit);
		Attribute.addAttributeReader("RuntimeVisibleAnnotations", r);

		InputStream openInputStream= fileUnit.getClassFile().openInputStream();

		String filename= fileUnit.getName();
		byte[] originalByteArray= IOUtils.toByteArray(openInputStream);
		byte[] transformedArray= originalByteArray;

		transformedArray= lambdaUsageBackporter.transform(filename, transformedArray);

		if (DragomeJsCompiler.compiler.bytecodeTransformer != null)
			if (DragomeJsCompiler.compiler.bytecodeTransformer.requiresTransformation(filename))
				transformedArray= DragomeJsCompiler.compiler.bytecodeTransformer.transform(filename, transformedArray);

		fileUnit.setBytecodeArrayI(transformedArray);

		ClassParser cp= new ClassParser(new ByteArrayInputStream(transformedArray), filename);
		jc= cp.parse();

	}

	private Map<String, String> getAnnotationsValues(Collection<AnnotationEntry> entries)
	{
		Map<String, String> result= new LinkedHashMap<String, String>();
		for (AnnotationEntry entry : entries)
		{
			if (entry.getElementValuePairs().length == 0)
			{
				result.put(Type.getType(entry.getAnnotationType()) + "# ", " ");
				continue;
			}
			for (ElementValuePair pair : entry.getElementValuePairs())
			{

				result.put(Type.getType(entry.getAnnotationType()) + "#" + pair.getNameString(), pair.getValue().toString());
			}

		}

		return result;
	}

	public void parseMethod(TypeDeclaration typeDecl, MethodDeclaration methodDecl, Method method)
	{
		Type[] types= method.getArgumentTypes();

		int offset;
		if (Modifier.isStatic(methodDecl.getAccess()))
		{
			offset= 0;
		}
		else
		{

			offset= 1;
		}
		for (int i= 0; i < types.length; i++)
		{
			VariableDeclaration variableDecl= new VariableDeclaration(VariableDeclaration.LOCAL_PARAMETER);
			variableDecl.setName(VariableDeclaration.getLocalVariableName(method, offset, 0));
			variableDecl.setType(types[i]);
			methodDecl.addParameter(variableDecl);
			offset+= types[i].getSize();
		}

		if (methodDecl.getCode() == null)
			return;

		Log.getLogger().debug("Parsing " + methodDecl.toString());
		Pass1 pass1= new Pass1(jc);
		ASTNode node= null;

		try
		{
			pass1.parse(method, methodDecl);
		}
		catch (UnhandledCompilerProblemException ex)
		{
			Pass1.setClassNotReversible(methodDecl);
		}

		catch (ParseException e)
		{
			node= e.getAstNode();
		}
		catch (Exception e)
		{
			node= Pass1.getCurrentNode();

			if (DragomeJsCompiler.compiler.isFailOnError())
			{
				throw Utils.generateException(e, methodDecl, node);
			}
			else
			{
				fileUnit.addNotReversibleMethod(Pass1.extractMethodNameSignature(methodDecl.getMethodBinding()));
				//String msg= Utils.generateExceptionMessage(methodDecl, node);
				//DragomeJsCompiler.errorCount++;
				//		    Log.getLogger().error(msg + "\n" + Utils.stackTraceToString(ex));
			}

		}
		Block body= new Block();
		/*
		MethodBinding binding= MethodBinding.lookup("java.lang.RuntimeException", "<init>", "(java/lang/String)V;");
		ClassInstanceCreation cic= new ClassInstanceCreation(methodDecl, binding);
		cic.addArgument(new StringLiteral("Unresolved decompilation problem"));
		throwStmt.setExpression(cic);
		body.appendChild(throwStmt);*/
		methodDecl.setBody(body);

		if (DragomeJsCompiler.compiler.optimize && methodDecl.getBody().getLastChild() instanceof ReturnStatement)
		{
			ReturnStatement ret= (ReturnStatement) methodDecl.getBody().getLastChild();
			if (ret.getExpression() == null)
			{
				methodDecl.getBody().removeChild(ret);
			}
		}
	}

	private static List<AnnotationEntry> collectEntries(Attribute... attributes)
	{
		List<AnnotationEntry> annotations= new ArrayList<>();
		Annotations annotation;
		for (Attribute attribute : attributes)
		{
			if (attribute instanceof Annotations)
			{
				annotation= (Annotations) attribute;

				annotations.addAll(Arrays.asList(annotation.getAnnotationEntries()));

			}
		}
		return annotations;
	}

	public ConstantPool getConstantPool()
	{
		return jc.getConstantPool();
	}

	public String toString()
	{
		return jc.getClassName();
	}

}
