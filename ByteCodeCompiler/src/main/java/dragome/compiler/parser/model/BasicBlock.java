package dragome.compiler.parser.model;

import java.util.LinkedList;

import org.objectweb.asm.Label;

public class BasicBlock
{
	private final Label identifier;
	private final int labelPosition;

	private LinkedList<OpCodeIns> instructions= new LinkedList<>();
	private Scope positionFor= Scope.UNINITIALIZED;
	private Variable target;

	public BasicBlock(Label identifier, int labelPosition)
	{
		this.identifier= identifier;
		this.labelPosition= labelPosition;
	}

	public LinkedList<OpCodeIns> getInstructions()
	{
		return instructions;
	}

	public void addInstruction(OpCodeIns instruction)
	{
		instructions.add(instruction);
	}

	public Scope getScope()
	{
		return positionFor;
	}

	public void setPositionFor(Scope positionFor)
	{
		this.positionFor= positionFor;
	}

	public Label getIdentifier()
	{
		return identifier;
	}

	public int getMethodPosition()
	{
		return labelPosition;
	}

	public Variable getTarget()
	{
		return target;
	}

	public void setTarget(Variable operator)
	{
		this.target= operator;
	}

	public int getLabelPosition()
	{
		return labelPosition;
	}

}
