package dragome.compiler.parser.visitors;

import org.objectweb.asm.ClassVisitor;

public abstract class AbstractClassVisitor extends ClassVisitor implements ClassViewParser
{

	public AbstractClassVisitor(int api)
	{
		super(api);

	}

}