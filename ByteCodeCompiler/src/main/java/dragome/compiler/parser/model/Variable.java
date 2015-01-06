package dragome.compiler.parser.model;

import dragome.compiler.model.typeinfo.TypeInfo;

public final class Variable
{
	private final String name;
	private final String typeName;
	private final Scope context;
	private final int index;
	private final TypeInfo type;

	public Variable(String name, String typeName, Scope context, TypeInfo type, int index)
	{

		this.name= name;
		this.typeName= typeName;
		this.context= context;
		this.index= index;
		this.type= type;
	}

	public String getName()
	{
		return name;
	}

	public String getTypeName()
	{
		return typeName;
	}

	public Scope getContext()
	{
		return context;
	}

	public int getIndex()
	{
		return index;
	}

	public TypeInfo getType()
	{
		return type;
	}

}
