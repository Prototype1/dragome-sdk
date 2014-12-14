package com.dragome.compiler.parser.advanced;

import java.util.List;

import org.objectweb.asm.ClassReader;

import com.dragome.compiler.exceptions.FatalParseException;
import com.dragome.compiler.exceptions.ServerInClientCode;
import com.dragome.compiler.model.valueTypes.ClassValue;
import com.dragome.compiler.task.Parsable;

public class AsyncParser implements Parsable<ClassValue, String>
{

	private ClassReader reader;
	private AbstractByteCodeVisitor classVisitor;

	@Override
	public List<ClassValue> parse() throws ServerInClientCode, FatalParseException
	{
		reader.accept(classVisitor, 0);

		classVisitor.getClassView();

		return null;
	}
	@Override
	public void prepareParsing(String clazz) throws Exception
	{
		reader= new ClassReader(clazz);
		classVisitor= new DragomeClassVisitor();

	}

}
