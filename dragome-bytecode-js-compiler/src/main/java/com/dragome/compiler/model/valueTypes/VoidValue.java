package com.dragome.compiler.model.valueTypes;

import com.dragome.compiler.model.TypeInfo;
import com.dragome.compiler.model.ValueType;

public class VoidValue extends ValueType
{

	public VoidValue()
	{
		super(TypeInfo.VOID);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString()
	{
		
		return Void.TYPE.getName();
	}

}
