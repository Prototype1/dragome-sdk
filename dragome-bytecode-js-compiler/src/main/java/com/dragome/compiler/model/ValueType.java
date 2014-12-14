package com.dragome.compiler.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;

import com.dragome.compiler.model.valueTypes.AnnotationValue;

public abstract class ValueType
{

	private final TypeInfo typeInfo;
	private AccessModifier accessModifier;

	private final List<AnnotationValue> annotations= new ArrayList<>();
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

	public void addAnnotation(AnnotationValue object)
	{
		annotations.add(object);
	}

	public void addAnnotations(Collection<AnnotationValue> object)
	{
		annotations.addAll(object);
	}

	public void addAllElementModifiers(Collection<ElementModifier> modifiers)
	{
		elementModifiers.addAll(modifiers);
	}

	public Collection<AnnotationValue> getAnnotation()
	{
		return Collections.unmodifiableCollection(annotations);
	}

	public Collection<ElementModifier> getElementModifiers()
	{
		return Collections.unmodifiableCollection(elementModifiers);
	}

	public abstract String toString();

}
