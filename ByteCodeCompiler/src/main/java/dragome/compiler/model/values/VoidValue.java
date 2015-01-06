package dragome.compiler.model.values;

import dragome.compiler.model.typeinfo.TypeInfo;
import dragome.compiler.model.typeinfo.ValueType;

public class VoidValue extends ValueType
{
	private final static String name= Void.TYPE.getName();

	public VoidValue()
	{
		super(TypeInfo.VOID, name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString()
	{

		return name;
	}

}
