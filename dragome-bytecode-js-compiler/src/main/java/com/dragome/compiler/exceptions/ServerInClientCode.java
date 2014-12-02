package com.dragome.compiler.exceptions;

public class ServerInClientCode extends Exception
{

	/**
	 * 
	 */
	private static final long serialVersionUID= -7576634283217966457L;

	public ServerInClientCode(String message)
	{
		super(message);
	}

	public ServerInClientCode(Throwable reason)
	{
		super(reason);
	}

}
