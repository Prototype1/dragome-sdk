package com.dragome.compiler.model;

import java.util.List;

public class MethodHolder
{
	private final MethodReference reference;
	private final List<ValueType> args;

	public MethodHolder(MethodReference reference, List<ValueType> args)
	{
		this.reference= reference;
		this.args= args;
	}

	public MethodReference getReference()
	{
		return reference;
	}

	public List<ValueType> getArgs()
	{
		return args;
	}
	
}
