package com.dragome.compiler.task;

import java.util.concurrent.Callable;

import com.dragome.compiler.ast.TypeDeclaration;

/**
 * Probably needs some redesign just a prototype
 * @author Mo
 *
 * @param <T>
 */
public class ParserTask<T> implements Callable<TypeDeclaration>
{

	private final Parsable<T> parser;

	private Exception prepareFailReason= null;

	public ParserTask(Parsable<T> parser, T data)
	{
		this.parser= parser;
		try
		{
			parser.prepareParsing(data);
		}
		catch (Exception e)
		{
			prepareFailReason= e;
		}

	}

	@Override
	public TypeDeclaration call() throws Exception
	{
		if (prepareFailReason != null)
			throw prepareFailReason;

		return parser.parse();
	}
}
