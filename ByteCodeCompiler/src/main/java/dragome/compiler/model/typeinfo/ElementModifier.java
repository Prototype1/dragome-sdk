package dragome.compiler.model.typeinfo;

import java.util.EnumSet;

import org.objectweb.asm.Opcodes;

public enum ElementModifier
{
	ABSTRACT, ANNOTATION, BRIDGE, DEPRECATED, ENUM, FINAL, INTERFACE, NATIVE, STATIC, STRICT, SUPER, SYNCHRONIZED, SYNTHETIC, TRANSIENT, VARARGS, VOLATILE;

	public static EnumSet<ElementModifier> getModifiers(int acess)
	{
		EnumSet<ElementModifier> result= EnumSet.noneOf(ElementModifier.class);

		if (hasFlag(acess, Opcodes.ACC_ABSTRACT))
			result.add(ABSTRACT);
		if (hasFlag(acess, Opcodes.ACC_ANNOTATION))
			result.add(ANNOTATION);
		if (hasFlag(acess, Opcodes.ACC_BRIDGE))
			result.add(BRIDGE);
		if (hasFlag(acess, Opcodes.ACC_DEPRECATED))
			result.add(DEPRECATED);
		if (hasFlag(acess, Opcodes.ACC_ENUM))
			result.add(ENUM);
		if (hasFlag(acess, Opcodes.ACC_FINAL))
			result.add(FINAL);
		if (hasFlag(acess, Opcodes.ACC_INTERFACE))
			result.add(INTERFACE);
		if (hasFlag(acess, Opcodes.ACC_NATIVE))
			result.add(NATIVE);
		if (hasFlag(acess, Opcodes.ACC_STATIC))
			result.add(STATIC);
		if (hasFlag(acess, Opcodes.ACC_STRICT))
			result.add(STRICT);
		if (hasFlag(acess, Opcodes.ACC_SUPER))
			result.add(SUPER);
		if (hasFlag(acess, Opcodes.ACC_SYNCHRONIZED))
			result.add(SYNCHRONIZED);
		if (hasFlag(acess, Opcodes.ACC_SYNTHETIC))
			result.add(SYNTHETIC);
		if (hasFlag(acess, Opcodes.ACC_TRANSIENT))
			result.add(TRANSIENT);
		if (hasFlag(acess, Opcodes.ACC_VARARGS))
			result.add(VARARGS);
		if (hasFlag(acess, Opcodes.ACC_VOLATILE))
			result.add(VOLATILE);

		return result;
	}

	public static boolean hasFlag(int subject, int flag)
	{
		return (subject & flag) == flag;
	}

}
