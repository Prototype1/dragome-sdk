package com.dragome.compiler.parser.advanced;

import java.util.List;

import org.objectweb.asm.ClassReader;

import com.dragome.compiler.exceptions.FatalParseException;
import com.dragome.compiler.exceptions.ServerInClientCode;
import com.dragome.compiler.task.Parsable;

public class AsyncParser implements Parsable<String>
{

	private ClassReader reader;
	private AbstractByteCodeVisitor classVisitor;

	@Override
	public List<String> parse() throws ServerInClientCode, FatalParseException
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
