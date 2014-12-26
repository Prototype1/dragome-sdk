package dragome.compiler.model.values;

import dragome.compiler.model.ValueType;
import dragome.compiler.model.typeinfo.TypeInfo;

public class VoidValue extends ValueType
{

	public VoidValue()
	{
		super(TypeInfo.VOID);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString()
	{
		
		return Void.TYPE.getName();
	}

}
