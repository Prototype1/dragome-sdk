package com.dragome.compiler.model;

import com.dragome.compiler.model.valueTypes.ClassValue;

public class MethodReference
{
	private final ClassValue owner;
	private final String name;

	public ClassValue getOwner()
	{
		return owner;
	}

	public String getName()
	{
		return name;
	}

	public MethodReference(ClassValue owner, String name)
	{
		super();
		this.owner= owner;
		this.name= name;
	}

	@Override
	public int hashCode()
	{
		final int prime= 31;
		int result= 1;
		result= prime * result + ((name == null) ? 0 : name.hashCode());
		result= prime * result + ((owner == null) ? 0 : owner.hashCode());
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
		MethodReference other= (MethodReference) obj;
		if (name == null)
		{
			if (other.name != null)
				return false;
		}
		else if (!name.equals(other.name))
			return false;
		if (owner == null)
		{
			if (other.owner != null)
				return false;
		}
		else if (!owner.equals(other.owner))
			return false;
		return true;
	}

}
