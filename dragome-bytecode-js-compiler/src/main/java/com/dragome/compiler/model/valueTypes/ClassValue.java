package com.dragome.compiler.model.valueTypes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.dragome.compiler.model.MethodReference;
import com.dragome.compiler.model.ValueType;

/**
 * Subject of change. An class view for dragome carries everything the compiler should know to generate javascript out of it 
 * @author Mo
 *
 */

public class ClassValue extends ValueType
{
	private final String className;

	private List<ClassValue> implementsInterfaces= new ArrayList<>();
	private ClassValue extendsClass;

	private List<ClassValue> instanceVars;
	//TODO change object to something meaningful 
	private List<Object> methods= new ArrayList<>();
	private List<Object> classAnnotations= new ArrayList<>();

	//needed? tells you in which context this objects are used 
	private List<MethodReference> usedInContext= new ArrayList<>();

	public ClassValue(String className)
	{
		super(true);
		if (className == null)
			throw new IllegalArgumentException("ClassView could not be created");

		this.className= className;
	}

	//List operations. Returns only a view of the Lists to avoid unwanted manipulations 

	public ClassValue addMethods(Collection<Object> methods)
	{
		if (methods != null)
			this.methods.addAll(methods);

		return this;
	}

	public ClassValue addClassAnnotations(Collection<Object> classAnnotation)
	{

		if (classAnnotation != null)
			this.classAnnotations.addAll(classAnnotation);

		return this;
	}

	public ClassValue addInterfaces(Collection<ClassValue> interfaces)
	{

		if (interfaces != null)
			this.implementsInterfaces.addAll(interfaces);

		return this;
	}

	public void addExtendedClass(ClassValue extendsClass)
	{
		this.extendsClass= extendsClass;
	}

	public boolean hasInstanceVars()
	{
		return !instanceVars.isEmpty();
	}

	public boolean hasMethod()
	{
		return !methods.isEmpty();
	}

	public boolean implementsInterface()
	{
		return !implementsInterfaces.isEmpty();
	}

	public boolean hasClassAnnotation()
	{
		return !classAnnotations.isEmpty();
	}

	public Collection<ClassValue> getDependenciesToParse()
	{
		List<ClassValue> l= new ArrayList<>(implementsInterfaces);

		if (extendsClass != null)
			l.add(extendsClass);

		return Collections.unmodifiableList(l);

	}

}