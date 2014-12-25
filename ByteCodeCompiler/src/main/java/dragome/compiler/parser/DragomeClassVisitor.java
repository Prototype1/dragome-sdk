package dragome.compiler.parser;

import org.objectweb.asm.Opcodes;
import dragome.compiler.model.values.ClassValue;

public class DragomeClassVisitor extends AbstractByteCodeVisitor
{

	private ClassValue classView;

	public DragomeClassVisitor()
	{
		super(Opcodes.ASM5);

	}

	@Override
	public void visit(int version, int access, String name, String signature, String superName, String[] interfaces)
	{

		classView= new ClassValue(name);

		super.visit(version, access, name, signature, superName, interfaces);
	}

	@Override
	public ClassValue getClassView()
	{
		// TODO Auto-generated method stub
		return classView;
	}

}
