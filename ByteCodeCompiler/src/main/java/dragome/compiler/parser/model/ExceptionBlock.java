package dragome.compiler.parser.model;

import org.objectweb.asm.Label;

public class ExceptionBlock
{
	private Label startBlock, endBlock, catchBlock;
	private String type;

	public Label getStartBlock()
	{
		return startBlock;
	}
	public void setStartBlock(Label startBlock)
	{
		this.startBlock= startBlock;
	}
	public Label getEndBlock()
	{
		return endBlock;
	}
	public void setEndBlock(Label endBlock)
	{
		this.endBlock= endBlock;
	}
	public Label getCatchBlock()
	{
		return catchBlock;
	}
	public void setCatchBlock(Label catchBlock)
	{
		this.catchBlock= catchBlock;
	}
	public String getType()
	{
		return type;
	}
	public void setType(String type)
	{
		this.type= type;
	}
}
