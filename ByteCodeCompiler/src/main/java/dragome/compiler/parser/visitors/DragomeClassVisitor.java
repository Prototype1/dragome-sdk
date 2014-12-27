package dragome.compiler.parser.visitors;

import java.util.Collection;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import dragome.compiler.model.method.MethodInfo;
import dragome.compiler.model.values.ClassValue;

public class DragomeClassVisitor extends AbstractClassVisitor
{

	private ClassValue classView;
	private final Collection<MethodInfo> methodsToParse;

	public DragomeClassVisitor(Collection<MethodInfo> methodsToParse)
	{
		super(Opcodes.ASM5);
		this.methodsToParse= methodsToParse;

	}

	@Override
	public void visit(int version, int access, String name, String signature, String superName, String[] interfaces)
	{

		classView= new ClassValue(name);

		super.visit(version, access, name, signature, superName, interfaces);
	}

	@Override
	public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions)
	{

		if (!methodsToParse.contains(new MethodInfo(desc)))
			return null;
		//owns method visitor
		return super.visitMethod(access, name, desc, signature, exceptions);
	}
	@Override
	public ClassValue getClassView()
	{
		// TODO Auto-generated method stub
		return classView;
	}

}
