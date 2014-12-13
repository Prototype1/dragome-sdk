package com.dragome.compiler.model;

import java.util.Collections;
import java.util.List;

public class MethodHolder
{
	private final MethodReference reference;
	private final List<ValueType> args;

	private String argString;

	public MethodHolder(MethodReference reference, List<ValueType> args)
	{
		this.reference= reference;
		this.args= args;
	}

	public MethodReference getReference()
	{
		return reference;
	}

	public List<ValueType> getArgs()
	{
		return Collections.unmodifiableList(args);
	}

	@Override
	public String toString()
	{

		if (argString == null)
		{
			StringBuilder bu= new StringBuilder();

			for (ValueType valueType : args)
			{
				bu.append(valueType.toString());
			}

			argString= bu.toString();

		}

		return argString;
	}

	@Override
	public int hashCode()
	{
		return reference.hashCode() + toString().hashCode();
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj instanceof MethodHolder)
		{
			MethodHolder tmp= (MethodHolder) obj;

			return reference.equals(tmp) && toString().equals(tmp.toString());
		}

		return false;
	}

}
