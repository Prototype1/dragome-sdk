package dragome.compiler.parser;

import org.objectweb.asm.ClassVisitor;

public abstract class AbstractClassVisitor extends ClassVisitor implements ClassViewParser
{

	public AbstractClassVisitor(int api)
	{
		super(api);

	}

}