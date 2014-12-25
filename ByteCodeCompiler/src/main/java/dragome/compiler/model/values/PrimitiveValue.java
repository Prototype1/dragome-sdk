package dragome.compiler.model.values;

import dragome.compiler.model.PrimitiveInfo;
import dragome.compiler.model.TypeInfo;
import dragome.compiler.model.ValueType;

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
