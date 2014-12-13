package com.dragome.compiler.model.valueTypes;

import com.dragome.compiler.model.TypeInfo;
import com.dragome.compiler.model.ValueType;

public class ArrayValue extends ValueType
{

	private String typeOfArray;

	public ArrayValue()
	{
		super(TypeInfo.ARRAY);

	}

	@Override
	public String toString()
	{
		return typeOfArray;
	}

}
