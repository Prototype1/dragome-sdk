package com.dragome.compiler.model.valueTypes;

import java.util.EnumMap;

import com.dragome.compiler.model.AnnotationInfo;
import com.dragome.compiler.model.TypeInfo;
import com.dragome.compiler.model.ValueType;

public class AnnotationValue extends ValueType
{

	private final EnumMap<AnnotationInfo, String> paramHolder= new EnumMap<>(AnnotationInfo.class);

	public AnnotationValue()
	{
		super(TypeInfo.ANNOTATION);

	}

	@Override
	public String toString()
	{
		
		return paramHolder.keySet().toString();
	}

}
