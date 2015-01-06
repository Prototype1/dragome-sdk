package dragome.compiler.model.typeinfo;

import org.objectweb.asm.Type;

public enum PrimitiveInfo
{
	INT(Type.INT_TYPE), CHAR(Type.CHAR_TYPE), BYTE(Type.BYTE_TYPE), LONG(Type.LONG_TYPE), SHORT(Type.SHORT_TYPE), BOOLEAN(Type.BOOLEAN_TYPE), DOUBLE(Type.DOUBLE_TYPE), FLOAT(Type.FLOAT_TYPE);

	private final Type type;

	private PrimitiveInfo(Type type)
	{
		this.type= type;
	}

	public Type getType()
	{
		return type;
	}
	
	public static PrimitiveInfo createFromAsmType(Type t)
	{
		int type= t.getSort();

		switch (type)
		{
			case 1:
				return PrimitiveInfo.BOOLEAN;
			case 2:
				return PrimitiveInfo.CHAR;
			case 3:
				return PrimitiveInfo.BYTE;
			case 4:
				return PrimitiveInfo.SHORT;
			case 5:
				return PrimitiveInfo.INT;
			case 6:
				return PrimitiveInfo.FLOAT;
			case 7:
				return PrimitiveInfo.LONG;
			case 8:
				return PrimitiveInfo.DOUBLE;
			default:
				throw new IllegalArgumentException("Is not a primitiv: " + t);
		}

	}
}
