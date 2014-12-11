package com.dragome.compiler.global;

import java.util.HashMap;
import java.util.Map;
/**
 * Thread safe 
 * @author Mo
 *
 */
public class ParsedClasses
{

	private static ParsedClasses INSTANCE= new ParsedClasses();

	public static ParsedClasses ACCESS()
	{
		return INSTANCE;
	}

	private Map<String, Class<?>> classes= new HashMap<>();

	private ParsedClasses()
	{
		// TODO Auto-generated constructor stub
	}

	public synchronized void putClass(Class<?> clazz)
	{
		classes.put(clazz.getName(), clazz);
	}

	public Class<?> getClass(String name)
	{
		return classes.get(name);
	}

}
