package dragome.compiler.parser.task;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * Probably needs some redesign just a prototype
 * @author Mo
 *
 * @param <T>
 */
public class ParserTask<R ,T> implements Callable<List<R>>
{

	private final Parsable<R, T> parser;

	private Exception prepareFailedReason= null;

	public ParserTask(Parsable<R, T> parser, T data)
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
	public List<R> call() throws Exception
	{
		if (prepareFailedReason != null)
			throw prepareFailedReason;

		return parser.parse();
	}
}
