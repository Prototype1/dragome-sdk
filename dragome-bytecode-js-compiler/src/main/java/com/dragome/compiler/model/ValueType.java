package com.dragome.compiler.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;

public abstract class ValueType
{

	private final TypeInfo typeInfo;
	private AccessModifier accessModifier;

	private final List<Object> annotations= new ArrayList<>();
	private final List<MethodReference> usedInContext= new ArrayList<>();
	private final EnumSet<ElementModifier> elementModifiers= EnumSet.noneOf(ElementModifier.class);

	public ValueType(TypeInfo typeInfo)
	{
		this.typeInfo= typeInfo;

	}

	public TypeInfo getTypeInfo()
	{
		return typeInfo;
	}

	public AccessModifier getAccessModifier()
	{
		return accessModifier;
	}

	public void setAccessModifier(AccessModifier accessModifier)
	{
		this.accessModifier= accessModifier;
	}

	public void addAnnotation(Object object)
	{
		annotations.add(object);
	}

	public void addAnnotations(Collection<Object> object)
	{
		annotations.addAll(object);
	}

	public Collection<Object> getAnnotation()
	{
		return Collections.unmodifiableCollection(annotations);
	}

}
