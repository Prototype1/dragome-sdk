package dragome.compiler.model.values;

import dragome.compiler.model.typeinfo.PrimitiveInfo;
import dragome.compiler.model.typeinfo.TypeInfo;
import dragome.compiler.model.typeinfo.ValueType;

public class PrimitiveValue extends ValueType
{

	private final PrimitiveInfo valueInfo;

	public PrimitiveValue(PrimitiveInfo valueInfo, String name)
	{
		super(TypeInfo.PRIMITIV, name);
		this.valueInfo= valueInfo;

	}

	@Override
	public String toString()
	{

		return valueInfo.name();
	}

}
