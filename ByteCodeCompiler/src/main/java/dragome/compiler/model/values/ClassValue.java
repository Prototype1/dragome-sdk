package dragome.compiler.model.values;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import dragome.compiler.model.MethodHolder;
import dragome.compiler.model.TypeInfo;
import dragome.compiler.model.ValueType;

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

	private List<ValueType> instanceVars= new ArrayList<>();
	private List<MethodHolder> methods= new ArrayList<>();

	public ClassValue(String className)
	{
		super(TypeInfo.OBJECT);
		if (className == null)
			throw new IllegalArgumentException("ClassView could not be created");

		this.className= className;
	}

	//List operations. Returns only a view of the Lists to avoid unwanted manipulations 

	public ClassValue addMethods(Collection<MethodHolder> methods)
	{
		if (methods != null)
			this.methods.addAll(methods);

		return this;
	}

	public ClassValue addClassAnnotations(Collection<AnnotationValue> classAnnotation)
	{

		if (classAnnotation != null)
			addAnnotations(classAnnotation);

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
		return !getAnnotation().isEmpty();
	}

	public Collection<ClassValue> getDependenciesToParse()
	{
		List<ClassValue> l= new ArrayList<>(implementsInterfaces);

		if (extendsClass != null)
			l.add(extendsClass);

		return Collections.unmodifiableList(l);

	}

	public String getClassName()
	{
		return className;
	}

	@Override
	public int hashCode()
	{
		final int prime= 31;
		int result= 1;
		result= prime * result + ((className == null) ? 0 : className.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClassValue other= (ClassValue) obj;
		if (className == null)
		{
			if (other.className != null)
				return false;
		}
		else if (!className.equals(other.className))
			return false;
		return true;
	}

	@Override
	public String toString()
	{

		return className;
	}

}
