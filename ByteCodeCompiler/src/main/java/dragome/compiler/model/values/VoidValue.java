package dragome.compiler.model.values;

import dragome.compiler.model.typeinfo.TypeInfo;
import dragome.compiler.model.typeinfo.ValueType;

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
