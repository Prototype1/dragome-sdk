package com.dragome.compiler.parser.advanced;

import org.objectweb.asm.Opcodes;

import com.dragome.compiler.model.valueTypes.ClassValue;

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
