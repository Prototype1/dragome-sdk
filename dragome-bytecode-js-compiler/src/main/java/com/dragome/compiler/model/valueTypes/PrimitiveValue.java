package com.dragome.compiler.model.valueTypes;

import com.dragome.compiler.model.PrimitiveInfo;
import com.dragome.compiler.model.TypeInfo;
import com.dragome.compiler.model.ValueType;

public class PrimitiveValue extends ValueType
{

	private final PrimitiveInfo valueInfo;

	public PrimitiveValue(PrimitiveInfo valueInfo)
	{
		super(TypeInfo.Primitiv);
		this.valueInfo= valueInfo;

	}

	@Override
	public String toString()
	{

		return valueInfo.name();
	}

}
