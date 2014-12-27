package dragome.compiler.model.method;

/**
 * Used to get an overview of the used methods in a class. 
 * @author Mo
 *
 */
public class MethodInfo
{

	private final String desc;

	public MethodInfo(String desc)
	{
		this.desc= desc;
	}

	@Override
	public int hashCode()
	{
		final int prime= 31;
		int result= 1;
		result= prime * result + ((desc == null) ? 0 : desc.hashCode());
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
		return true;
	}

	public String getDesc()
	{
		return desc;
	}

}
