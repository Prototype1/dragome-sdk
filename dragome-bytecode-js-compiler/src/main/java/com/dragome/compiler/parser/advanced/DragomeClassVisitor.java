package com.dragome.compiler.parser.advanced;

import org.objectweb.asm.Opcodes;

public class DragomeClassVisitor extends AbstractByteCodeVisitor
{

	
	private  ClassView classView; 
	
	
	public DragomeClassVisitor()
	{
		super(Opcodes.ASM5);
		


	}
	
	@Override
	public void visit(int version, int access, String name, String signature, String superName, String[] interfaces)
	{
		
		classView = new ClassView(name);
		
		
		
		super.visit(version, access, name, signature, superName, interfaces);
	}
	

	@Override
	public ClassView getClassView()
	{
		// TODO Auto-generated method stub
		return null;
	}



}
