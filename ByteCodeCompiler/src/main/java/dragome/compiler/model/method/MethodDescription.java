package dragome.compiler.model.method;

public class MethodDescription
{
	private final String returnType;
	private final String[] paramters;

	public MethodDescription(String returnType, String[] paramters)
	{
		this.returnType= returnType;
		this.paramters= paramters;
	}

	public String[] getParamters()
	{
		return paramters;
	}

	public String getReturnType()
	{
		return returnType;
	}

}
