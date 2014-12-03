package com.dragome.compiler.threadpool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * An Enum that creates a ThreadPool, you could create different thread pools for different tasks,
 * which is however not recommended
 * @author Mo
 *
 */
public enum ThreadPool
{
	POOL;

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

	public void shutdownSafty() throws InterruptedException
	{
		executor.shutdown();

	}

	public boolean isShutDown()
	{
		return executor.isShutdown();
	}
}
