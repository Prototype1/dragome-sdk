package dragome.compiler.model.values;

import dragome.compiler.model.ValueType;
import dragome.compiler.model.typeinfo.TypeInfo;

public class ArrayValue extends ValueType
{

	private String typeOfArray;

	public ArrayValue()
	{
		super(TypeInfo.ARRAY);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString()
	{
		return typeOfArray;
	}

}
