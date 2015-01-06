package dragome.compiler.model.values;

import dragome.compiler.model.typeinfo.TypeInfo;
import dragome.compiler.model.typeinfo.ValueType;

public class ArrayValue extends ValueType
{

	private String typeOfArray;

	public ArrayValue(String name)
	{
		super(TypeInfo.ARRAY, name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString()
	{
		return typeOfArray;
	}

}
