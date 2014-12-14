package com.dragome.compiler.task;

import java.util.List;
import java.util.concurrent.Callable;

import com.dragome.compiler.ast.TypeDeclaration;

/**
 * Probably needs some redesign just a prototype
 * @author Mo
 *
 * @param <T>
 */
public class ParserTask<T> implements Callable<List<T>>
{

	private final Parsable<T> parser;

	private Exception prepareFailedReason= null;

	public ParserTask(Parsable<T> parser, T data)
	{
		this.parser= parser;
		try
		{
			parser.prepareParsing(data);
		}
		catch (Exception e)
		{
			prepareFailedReason= e;
		}

	}

	@Override
	public List<T> call() throws Exception
	{
		if (prepareFailedReason != null)
			throw prepareFailedReason;

		return parser.parse();
	}
}
