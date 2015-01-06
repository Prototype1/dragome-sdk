package dragome.compiler.parser.model;

import org.objectweb.asm.Label;

public enum OpCodeParaInfo
{

	INDEX(Integer.class), NAME(String.class), OWNER(String.class), LABEL(Label.class), OBJECT_VALUE(Object.class), INVOKE_TYPE(InvokeType.class), ARRAY_TYPE(Object.class), PRIMITIV_VALUE(Object.class), EXCEPTION(ExceptionBlock.class);

	private final Class<?> clazz;

	private OpCodeParaInfo(Class<?> clazz)
	{
		this.clazz= clazz;
	}

	public boolean isClassOrSubclass(Object o)
	{

		return o.getClass().isAssignableFrom(clazz) || o.getClass() == clazz;
	}

	@SuppressWarnings("unchecked")
	public <T> T getType(Object o)
	{

		if (isClassOrSubclass(o))
			return (T) o;
		throw new IllegalArgumentException("Object" + o + " has an invalid Type");
	}

}
