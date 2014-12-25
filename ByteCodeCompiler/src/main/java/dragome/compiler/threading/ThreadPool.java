package dragome.compiler.threading;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * An Enum that creates a ThreadPool, you could create different thread pools for different tasks,
 * which is however not recommended
 * @author Mo
 *
 */
public enum ThreadPool
{
	DEFAULT_POOL, PARSER_POOL;

	public final int TOTAL_THREAD_COUNT= Runtime.getRuntime().availableProcessors();
	private final ThreadPoolExecutor executor;

	private ThreadPool()
	{

		executor= (ThreadPoolExecutor) Executors.newFixedThreadPool(TOTAL_THREAD_COUNT);
	}

	public <T> Future<T> submitTask(Callable<T> task)
	{
		return executor.submit(task);
	}

	public boolean hasTasks()
	{
		return executor.getActiveCount() != 0;
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
