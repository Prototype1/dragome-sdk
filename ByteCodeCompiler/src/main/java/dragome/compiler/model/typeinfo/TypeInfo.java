package dragome.compiler.model.typeinfo;

import org.objectweb.asm.Type;

public enum TypeInfo
{
	OBJECT, ARRAY, ANNOTATION, VOID, PRIMITIV, METHOD, NOTHING;

	public TypeInfo createFromASMType(Type type)
	{

		int t= type.getSort();

		switch (t)
		{
			case 0:
				return TypeInfo.VOID;

			case 9:
				return TypeInfo.ARRAY;
			case 10:
				return TypeInfo.OBJECT;
			case 11:
				return TypeInfo.METHOD;

			default:

				if (t >= 1 && t <= 8)
					return TypeInfo.PRIMITIV;
				return NOTHING;

		}
	}
}
