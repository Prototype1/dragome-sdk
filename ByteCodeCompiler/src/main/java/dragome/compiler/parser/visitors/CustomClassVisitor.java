package dragome.compiler.parser.visitors;

import java.util.ArrayList;
import java.util.List;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AnnotationNode;
import org.objectweb.asm.tree.MethodNode;

import dragome.compiler.parser.model.ClassVars;

public class CustomClassVisitor extends ClassVisitor
{

	private List<MethodNode> methods= new ArrayList<>();
	private List<AnnotationNode> annotations= new ArrayList<>();

	private final ClassVars variables= new ClassVars();

	public CustomClassVisitor()
	{
		super(Opcodes.ASM5);
	}

	@Override
	public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions)
	{

		//MethodInfo info= new MethodInfo(desc, owner, name);

		return new CustomMethodVisitor(null);
	}

	@Override
	public AnnotationVisitor visitAnnotation(String desc, boolean visible)
	{

		if (visible)
		{
			AnnotationNode an= new AnnotationNode(desc);

			annotations.add(an);
			return an;
		}

		return null;
	}

	@Override
	public FieldVisitor visitField(int access, String name, String desc, String signature, Object value)
	{

		System.out.println("visitField");
		System.out.println(name);

		return super.visitField(access, name, desc, signature, value);
	}

	@Override
	public void visitAttribute(Attribute attr)
	{

		// TODO Auto-generated method stub
		super.visitAttribute(attr);
	}

	@Override
	public void visitEnd()
	{

	}
	public List<MethodNode> getMethods()
	{
		return methods;
	}

	public List<AnnotationNode> getAnnotations()
	{
		return annotations;
	}

}
