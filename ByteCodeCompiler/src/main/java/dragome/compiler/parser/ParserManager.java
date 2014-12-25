package dragome.compiler.parser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import dragome.compiler.model.values.ClassValue;
import dragome.compiler.parser.task.ParserTask;
import dragome.compiler.threading.ThreadPool;

public class ParserManager
{

	private final String startingClass;

	public ParserManager(String startingClass)
	{
		this.startingClass= startingClass;
	}

	public void startParsing()
	{
		ParserTask<ClassValue, String> startingTask= new ParserTask<ClassValue, String>(new AsyncParser(), startingClass);

		Future<List<ClassValue>> result= ThreadPool.PARSER_POOL.submitTask(startingTask);

		List<Future<List<ClassValue>>> futures= new ArrayList<>();
		List<ClassValue> finishedResults= new ArrayList<>();

		futures.add(result);

		do
		{

			Iterator<Future<List<ClassValue>>> iter= futures.iterator();

			while (iter.hasNext())
			{
				Future<List<ClassValue>> currFuture= iter.next();

				if (currFuture.isDone())
				{
					finishedResults.addAll(getFutureAndHandleError(currFuture));
					iter.remove();

				}
				else if (currFuture.isCancelled())
				{
					iter.remove();
				}

			}

			for (ClassValue currClass : finishedResults)
			{
				//				ParsedClasses.ACCESS().putClass(currClass);

				Future<List<ClassValue>> future= ThreadPool.PARSER_POOL.submitTask(new ParserTask<ClassValue, String>(new AsyncParser(), currClass.getClassName()));
				futures.add(future);

			}

			//all parsed classes were handeled 
			finishedResults.clear();

		}
		while (ThreadPool.PARSER_POOL.hasTasks());

	}

	private List<ClassValue> getFutureAndHandleError(Future<List<ClassValue>> future)
	{
		try
		{
			return future.get();
		}
		catch (InterruptedException | ExecutionException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return Collections.emptyList();
	}

}
