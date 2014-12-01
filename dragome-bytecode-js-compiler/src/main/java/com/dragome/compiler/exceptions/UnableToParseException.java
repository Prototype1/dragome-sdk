package com.dragome.compiler.exceptions;

public class UnableToParseException extends Exception
{

	/**
	 * 
	 */
	private static final long serialVersionUID= -7576634283217966457L;

	public UnableToParseException(String message)
	{
		super(message);
	}

	public UnableToParseException(Throwable reason)
	{
		super(reason);
	}

}
