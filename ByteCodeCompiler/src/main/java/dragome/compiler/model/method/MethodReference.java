package dragome.compiler.model.method;

import dragome.compiler.model.values.ClassValue;

public class MethodReference
{
	private final ClassValue classOwner;
	private final String methodName;

	public MethodReference(ClassValue classOwner, String methodName)
	{

		this.classOwner= classOwner;
		this.methodName= methodName;
	}

	public String getMethodName()
	{
		return methodName;
	}

	public ClassValue getClassOwner()
	{
		return classOwner;
	}
}