package com.dragome.compiler.threadpool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public enum ThreadPool
{
	INSTANCE;

	public final int TOTAL_THREAD_COUNT= Runtime.getRuntime().availableProcessors();
	private final ExecutorService executor;

	private ThreadPool()
	{

		executor= Executors.newFixedThreadPool(TOTAL_THREAD_COUNT);
	}

	public <T> Future<T> submitTask(Callable<T> task)
	{
		return executor.submit(task);
	}

}
