package dragome.compiler.parser;

import java.util.List;

import org.objectweb.asm.ClassReader;

import dragome.compiler.parser.task.Parsable;

public class AsyncParser implements Parsable<ClassValue, String>
{

	private ClassReader reader;
	private AbstractByteCodeVisitor classVisitor;

	@Override
	public List<ClassValue> parse() 
	{
		reader.accept(classVisitor, 0);

		classVisitor.getClassView();

		return null;
	}
	@Override
	public void prepareParsing(String className) throws Exception
	{
		reader= new ClassReader(className);
		classVisitor= new DragomeClassVisitor();

	}

}
