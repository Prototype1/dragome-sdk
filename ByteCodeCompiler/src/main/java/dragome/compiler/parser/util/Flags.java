package dragome.compiler.parser.util;

public class Flags
{
	public static boolean hasFlag(int subject, int flag)
	{
		return (subject & flag) == flag;
	}
}
