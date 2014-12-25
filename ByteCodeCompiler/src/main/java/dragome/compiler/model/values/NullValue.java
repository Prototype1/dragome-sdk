package dragome.compiler.model.values;

import dragome.compiler.model.TypeInfo;
import dragome.compiler.model.ValueType;

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
