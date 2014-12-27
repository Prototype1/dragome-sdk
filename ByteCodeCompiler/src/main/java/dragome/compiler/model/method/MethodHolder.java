package dragome.compiler.model.method;

public class MethodHolder
{
	private final MethodReference info;
	private final MethodDescription des;

	public MethodHolder(MethodReference info, MethodDescription des)
	{
		this.info= info;
		this.des= des;
	}

	public MethodDescription getDes()
	{
		return des;
	}

	public MethodReference getInfo()
	{
		return info;
	}

}
