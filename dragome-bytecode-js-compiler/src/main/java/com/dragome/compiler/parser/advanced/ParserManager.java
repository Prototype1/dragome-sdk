package com.dragome.compiler.parser.advanced;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import com.dragome.compiler.global.ParsedClasses;
import com.dragome.compiler.task.ParserTask;
import com.dragome.compiler.threadpool.ThreadPool;

public class ParserManager
{

	private final String startingClass;

	public ParserManager(String startingClass)
	{
		this.startingClass= startingClass;
	}

	public void startParsing()
	{
		ParserTask<String> startingTask= new ParserTask<String>(new AsyncParser(), startingClass);

		Future<List<String>> result= ThreadPool.PARSER_POOL.submitTask(startingTask);

		List<Future<List<String>>> futures= new ArrayList<>();
		List<String> finishedResults= new ArrayList<>();

		futures.add(result);

		do
		{

			Iterator<Future<List<String>>> iter= futures.iterator();

			while (iter.hasNext())
			{
				Future<List<String>> currFuture= iter.next();

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

			for (String currClass : finishedResults)
			{
				//				ParsedClasses.ACCESS().putClass(currClass);

				Future<List<String>> future= ThreadPool.PARSER_POOL.submitTask(new ParserTask<String>(new AsyncParser(), currClass));
				futures.add(future);

			}

			//all parsed classes were handeled 
			finishedResults.clear();

		}
		while (ThreadPool.PARSER_POOL.hasTasks());

	}

	private List<String> getFutureAndHandleError(Future<List<String>> future)
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
