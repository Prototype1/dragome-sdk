package dragome.compiler.model.method;

import dragome.compiler.parser.model.InvokeType;

/**
 * Used to get an overview of the used methods in a class. 
 * @author Mo
 *
 */
public class MethodInfo
{

	private final String desc;
	private final String owner;
	private final String name;
	private final InvokeType type;

	public MethodInfo(String desc, String owner, String name, InvokeType type)
	{

		this.desc= desc;
		this.owner= owner;
		this.name= name;
		this.type= type;
	}

	@Override
	public int hashCode()
	{
		final int prime= 31;
		int result= 1;
		result= prime * result + ((desc == null) ? 0 : desc.hashCode());
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
		MethodInfo other= (MethodInfo) obj;
		if (desc == null)
		{
			if (other.desc != null)
				return false;
		}
		else if (!desc.equals(other.desc))
			return false;
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

	public String getOwner()
	{
		return owner;
	}

	public String getName()
	{
		return name;
	}

	public String getDesc()
	{
		return desc;
	}

	public InvokeType getType()
	{
		return type;
	}

}
