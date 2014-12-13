package com.dragome.compiler.model;

import com.dragome.compiler.model.valueTypes.ClassValue;


public class MethodReference
{
	private final ClassValue owner;
	private final String name;

	public ClassValue getOwner()
	{
		return owner;
	}

	public String getName()
	{
		return name;
	}

	public MethodReference(ClassValue owner, String name)
	{
		super();
		this.owner= owner;
		this.name= name;
	}

}
