package dragome.compiler.parser.model;

import java.util.EnumMap;

public class OpCodeIns
{
	private final int opcode;

	private final EnumMap<OpCodeParaInfo, Object> parameters= new EnumMap<>(OpCodeParaInfo.class);

	public OpCodeIns(int opcode, int index)
	{
		this.opcode= opcode;
		parameters.put(OpCodeParaInfo.INDEX, index);
	}

	public OpCodeIns(int opcode)
	{
		this(opcode, -1);
	}

	public <T> T getObjectOrThrow(OpCodeParaInfo info)
	{
		return info.getType(parameters.get(info));
	}

	public void addParameter(OpCodeParaInfo info, Object parameter)
	{
		parameters.put(info, parameter);
	}

	public EnumMap<OpCodeParaInfo, Object> getParameter()
	{
		return parameters;
	}

	public String getName()
	{
		return getObjectOrThrow(OpCodeParaInfo.NAME);
	}

	public int getIndex()
	{
		return getObjectOrThrow(OpCodeParaInfo.INDEX);
	}

	public int getOpcode()
	{
		return opcode;
	}

}
