package com.dragome.compiler.parser.advanced;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import com.dragome.compiler.global.ParsedClasses;
import com.dragome.compiler.task.ParserTask;
import com.dragome.compiler.threadpool.ThreadPool;

public class ParserManager
{

	private final Class<?> startingClass;

	public ParserManager(Class<?> startingClass)
	{
		this.startingClass= startingClass;
	}

	public void startParsing()
	{
		ParserTask<Class<?>> startingTask= new ParserTask<Class<?>>(new AsyncParser(), startingClass);

		Future<List<Class<?>>> result= ThreadPool.PARSER_POOL.submitTask(startingTask);

		List<Future<List<Class<?>>>> futures= new ArrayList<>();
		List<Class<?>> finishedResults= new ArrayList<>();

		futures.add(result);

		do
		{

			for (Future<List<Class<?>>> future : futures)
			{
				if (future.isDone())
					finishedResults.addAll(getFutureAndHandleError(future));
			}

			for (Class<?> currClass : finishedResults)
			{
				ParsedClasses.ACCESS().putClass(currClass);
				
				Future<List<Class<?>>> future= ThreadPool.PARSER_POOL.submitTask(new ParserTask<Class<?>>(new AsyncParser(), currClass));
				futures.add(future);

			}

		}
		while (ThreadPool.PARSER_POOL.hasTasks());

	}

	private List<Class<?>> getFutureAndHandleError(Future<List<Class<?>>> future)
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
