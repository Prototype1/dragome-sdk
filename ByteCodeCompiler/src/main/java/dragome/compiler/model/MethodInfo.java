package dragome.compiler.model;

/**
 * A class that holds infos about a method which should get parsed. The ClassVisitor needs this infos to know which method it should parse
 * not ready for overloaded methods yet
 * @author Mo
 *
 */
public class MethodInfo
{
	private final String name;
	private final int numberPararmeters;

	public MethodInfo(String name, int numberPararmeters)
	{
		super();
		this.name= name;
		this.numberPararmeters= numberPararmeters;
	}

	public MethodInfo(String name)
	{
		this.name= name;
		numberPararmeters= -1;
	}

	public static MethodInfo createConstructor()
	{
		return new MethodInfo("<init>");
	}

	@Override
	public int hashCode()
	{
		final int prime= 31;
		int result= 1;
		result= prime * result + ((name == null) ? 0 : name.hashCode());
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
		if (name == null)
		{
			if (other.name != null)
				return false;
		}
		else if (!name.equals(other.name))
			return false;
		return true;
	}

	public int getNumberPararmeters()
	{
		return numberPararmeters;
	}

	public String getName()
	{
		return name;
	}
}
