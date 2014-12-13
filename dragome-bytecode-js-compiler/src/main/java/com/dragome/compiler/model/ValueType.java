package com.dragome.compiler.model;

public abstract class ValueType
{

	private final boolean isObject;

	public ValueType(boolean isObject)
	{
		this.isObject= isObject;
	
	}

	public boolean isObject()
	{
		return isObject;
	}

}
