package com.dragome.compiler.parser.advanced;

import org.objectweb.asm.ClassVisitor;

public abstract class AbstractByteCodeVisitor extends ClassVisitor implements ClassViewParser
{

	public AbstractByteCodeVisitor(int api)
	{
		super(api);

	}

}