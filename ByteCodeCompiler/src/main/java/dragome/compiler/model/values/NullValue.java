package dragome.compiler.model.values;

import dragome.compiler.model.ValueType;
import dragome.compiler.model.typeinfo.TypeInfo;

public class NullValue extends ValueType
{

	public NullValue()
	{
		super(TypeInfo.NULL);

	}

	@Override
	public String toString()
	{
	
		return "null";
	}

}
