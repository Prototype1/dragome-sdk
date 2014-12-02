package com.dragome.compiler.exceptions;

/**
 * 
 * Fatal Exception which might happen while parsing a file and ressources are invalid or null. 
 * @author Mo
 *
 */
public class FatalParseException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID= -7162700754216667876L;

	public FatalParseException(String message)
	{
		super(message);
	}

	public FatalParseException(Throwable reason)
	{
		super(reason);
	}
}
