package dragome.compiler.model.values;

import dragome.compiler.model.ValueType;
import dragome.compiler.model.typeinfo.PrimitiveInfo;
import dragome.compiler.model.typeinfo.TypeInfo;

public class PrimitiveValue extends ValueType
{

	private final PrimitiveInfo valueInfo;

	public PrimitiveValue(PrimitiveInfo valueInfo)
	{
		super(TypeInfo.PRIMITIV);
		this.valueInfo= valueInfo;

	}

	@Override
	public String toString()
	{

		return valueInfo.name();
	}

}
