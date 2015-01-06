package dragome.compiler.parser.interfaces;

import dragome.compiler.parser.model.BasicBlock;

public interface Analytable<T>
{
	public T analyte(BasicBlock...instruction);
}
