package com.dragome.compiler.model;

import java.util.List;

public class MethodHolder
{
	private final MethodReference reference;
	private final List<ValueType> args;
	private final ValueType returnType;

	public MethodHolder(MethodReference reference, List<ValueType> args, ValueType returnType)
	{
		this.reference= reference;
		this.args= args;
		this.returnType= returnType;
	}

	public MethodReference getReference()
	{
		return reference;
	}

	public List<ValueType> getArgs()
	{
		return args;
	}

	public ValueType getReturnType()
	{
		return returnType;
	}

}
