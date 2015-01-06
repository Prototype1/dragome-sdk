package dragome.compiler.model.instruction;

import java.util.Set;

import com.google.common.collect.Sets;

public abstract class Instruction
{
	private final Set<Integer> ints;

	protected Instruction(Integer... ints)
	{

		this.ints= Sets.newHashSet(ints);
	}

	public abstract void visitThisInstruction(InstructionVisitor visitor);

	public Set<Integer> getInts()
	{
		return ints;
	}
}
