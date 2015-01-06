package dragome.compiler.model.typeinfo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;

import dragome.compiler.model.values.AnnotationValue;
import dragome.compiler.parser.model.Scope;

public abstract class ValueType
{

	private final TypeInfo typeInfo;
	private final List<AnnotationValue> annotations= new ArrayList<>();
	private final EnumSet<ElementModifier> elementModifiers= EnumSet.noneOf(ElementModifier.class);
	private final String name;

	private AccessModifier accessModifier;
	private Scope scope;
	private Object initValue;

	public ValueType(TypeInfo typeInfo, String name)
	{
		this.typeInfo= typeInfo;
		this.name= name;

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

	public void addElementModifier(ElementModifier modifier)
	{
		elementModifiers.add(modifier);
	}

	public Collection<AnnotationValue> getAnnotation()
	{
		return Collections.unmodifiableCollection(annotations);
	}

	public Collection<ElementModifier> getModifiers()
	{
		return Collections.unmodifiableCollection(elementModifiers);
	}

	public abstract String toString();

	public Object getInitValue()
	{
		return initValue;
	}

	public void setInitValue(Object initValue)
	{
		this.initValue= initValue;
	}

	public Scope getScope()
	{
		return scope;
	}

	public void setScope(Scope scope)
	{
		this.scope= scope;
	}

}
