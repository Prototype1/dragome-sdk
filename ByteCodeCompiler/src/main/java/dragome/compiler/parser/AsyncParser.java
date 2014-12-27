package dragome.compiler.parser;

import java.util.List;

import org.objectweb.asm.ClassReader;

import dragome.compiler.model.values.ClassValue;
import dragome.compiler.parser.task.Parsable;
import dragome.compiler.parser.visitors.AbstractClassVisitor;
import dragome.compiler.parser.visitors.DragomeClassVisitor;

public class AsyncParser implements Parsable<ClassValue, String>
{

	private ClassReader reader;
	private AbstractClassVisitor classVisitor;

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
		classVisitor= new DragomeClassVisitor(null);

	}

}