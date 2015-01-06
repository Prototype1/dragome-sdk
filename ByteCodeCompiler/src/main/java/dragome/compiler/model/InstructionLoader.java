package dragome.compiler.model;

import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.MethodNode;

import com.google.common.collect.Maps;

import dragome.compiler.model.instruction.Instruction;

public class InstructionLoader
{

	private final static String ROOT_PACKAGE= "dragome.compiler.model.instruction";
	private final static Class<Instruction> MASTER_CLASS= Instruction.class;

	private final Map<Integer, Class<? extends Instruction>> instructions= Maps.newHashMap();
	private final ClassLoader classLoader= MASTER_CLASS.getClassLoader();

	private InstructionLoader()
	{
		List<String> foundClasses= searchClasses(ROOT_PACKAGE);
		analyzeClasses(foundClasses);
	}

	private List<String> searchClasses(String packageName)
	{
		List<String> result= new LinkedList<>();

		URL url= Thread.currentThread().getContextClassLoader().getResource(packageName);

		Path packagePath= new File(url.getFile()).toPath();

		packagePath.forEach(e -> {

			String currFile= e.getFileName().toString();

			if (currFile.contains(".class"))
				result.add(packageName + currFile.replace(".class", ""));

		});

		return result;
	}

	private void analyzeClasses(List<String> classes)
	{

		classes.forEach(e -> {

			ClassReader cr= null;
			try
			{
				cr= new ClassReader(e);
			}
			catch (Exception e1)
			{
				e1.printStackTrace();
				return;
			}
			CustomClassVisitor node= new CustomClassVisitor();

			cr.accept(node, 0);

			if (node.isRightClass())
			{
				Class<?> currClazz= null;
				try
				{
					currClazz= classLoader.loadClass(e);
				}
				catch (Exception e1)
				{

					e1.printStackTrace();
					return;
				}

				Class<? extends Instruction> subClass= currClazz.asSubclass(MASTER_CLASS);

				//füge jeder analysierten value die gefundene classe als value hinzu
				node.getOpCodeValues().forEach(opcode -> instructions.put(opcode, subClass));
			}

		});

	}

	private class CustomClassVisitor extends ClassVisitor
	{

		private MethodVisitor constructorMethod;
		private final List<Integer> opcodeValues= new LinkedList<>();
		private boolean foundConstructor= false;

		public CustomClassVisitor()
		{
			super(Opcodes.ASM5);

		}

		@Override
		public void visit(int version, int access, String name, String signature, String superName, String[] interfaces)
		{
			//Implementiert diese Klasse unsere Master klasse? 
			if (MASTER_CLASS.getName().contains(superName))
				super.visit(version, access, name, signature, superName, interfaces);
		}
		@Override
		public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions)
		{
			//wir wollen nur den konstruktor haben und da auch nur den ersten
			if (!name.contains("<init>") || foundConstructor)
				return null;

			foundConstructor= true;

			constructorMethod= new MethodVisitor(Opcodes.ASM5)
			{

				private Label firstLabel;
				private boolean foundFirstLabel= false;

				@Override
				public void visitLabel(Label label)
				{
					if (!foundFirstLabel)
					{
						firstLabel= label;
						foundFirstLabel= true;
					}
					else
					{
						firstLabel= null;
					}

				}

				@Override
				public void visitIntInsn(int opcode, int operand)
				{
					if (firstLabel != null)
					{
						if (opcode == Opcodes.BIPUSH || opcode == Opcodes.SIPUSH)
							opcodeValues.add(operand);
					}
				}

			};

			return constructorMethod;
		}

		public List<Integer> getOpCodeValues()
		{
			return opcodeValues;
		}

		public boolean isRightClass()
		{
			return constructorMethod != null;
		}

	}
}
