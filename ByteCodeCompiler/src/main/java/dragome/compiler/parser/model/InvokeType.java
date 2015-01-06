package dragome.compiler.parser.model;

import org.objectweb.asm.Opcodes;

public enum InvokeType
{
	DYNAMIC, INTERFACE, SPECIAL, STATIC, VIRTUAL;

	public static InvokeType getFromOpCode(int opcode)
	{

		switch (opcode)
		{
			case Opcodes.INVOKEDYNAMIC:
				return DYNAMIC;
			case Opcodes.INVOKEINTERFACE:
				return INTERFACE;
			case Opcodes.INVOKESPECIAL:
				return SPECIAL;
			case Opcodes.INVOKESTATIC:
				return STATIC;
			case Opcodes.INVOKEVIRTUAL:
				return VIRTUAL;

			default:
				throw new RuntimeException("Illegaler Opcode: " + opcode);

		}

	}
}
