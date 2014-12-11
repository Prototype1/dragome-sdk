package com.dragome.compiler.parser.advanced;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Subject of change. An class view for dragome carries everything the compiler should know to generate javascript out of it 
 * @author Mo
 *
 */

public class ClassView
{
	private final String className;

	private List<ClassView> implementsInterfaces= new ArrayList<>();
	private ClassView extendsClass;

	private List<ClassView> instanceVars;
	//TODO change object to something meaningful 
	private List<Object> methods= new ArrayList<>();
	private List<Object> constructors= new ArrayList<>();
	private List<Object> classAnnotations= new ArrayList<>();

	//needed? tells you in which context this objects are used 
	private List<Object> usedInContext= new ArrayList<>();

	public ClassView(String className)
	{
		if (className == null)
			throw new IllegalArgumentException("ClassView could not be created");

		this.className= className;
	}

	//List operations. Returns only a view of the Lists to avoid unwanted manipulations 

	public ClassView addMethods(Collection<Object> methods)
	{
		if (methods != null)
			this.methods.addAll(methods);

		return this;
	}

	public ClassView addClassAnnotations(Collection<Object> classAnnotation)
	{

		if (classAnnotation != null)
			this.classAnnotations.addAll(classAnnotation);

		return this;
	}

	public ClassView addInterfaces(Collection<ClassView> interfaces)
	{

		if (interfaces != null)
			this.implementsInterfaces.addAll(interfaces);

		return this;
	}

	public void addExtendedClass(ClassView extendsClass)
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

	public Collection<ClassView> getDependenciesToParse()
	{
		List<ClassView> l= new ArrayList<>(implementsInterfaces);

		if (extendsClass != null)
			l.add(extendsClass);

		return Collections.unmodifiableList(l);

	}

}
