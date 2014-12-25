package dragome.compiler.parser.task;

import java.util.List;

public interface Parsable<R, T>
{
	public List<R> parse();

	public void prepareParsing(T t) throws Exception;
}
