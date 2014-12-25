package dragome.compiler.parser;

import org.objectweb.asm.ClassVisitor;

public abstract class AbstractByteCodeVisitor extends ClassVisitor implements ClassViewParser
{

	public AbstractByteCodeVisitor(int api)
	{
		super(api);

	}

}