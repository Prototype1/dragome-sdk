/*******************************************************************************
 * Copyright (c) 2011-2014 Fernando Petrola
 * 
 *  This file is part of Dragome SDK.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/
package java.lang;

import com.dragome.commons.javascript.ScriptHelper;

/**
 * The Long class wraps a value of the primitive type long in an object.
 *
 * 
 */
public final class Long
{
	public static final long MIN_VALUE= 0x8000000000000000L;

	/**
	 * A constant holding the maximum value a {@code long} can
	 * have, 2<sup>63</sup>-1.
	 */
	public static final long MAX_VALUE= 0x7fffffffffffffffL;

	public static final Class<Long> TYPE= Class.getType("long");

	private long value;

	/**
	 * Constructs a newly allocated Long object that represents the specified long argument.
	 */
	public Long(long l)
	{
		value= l;
	}

	public Long(String aString)
	{
		this(Integer.parseInt(aString));
	}

	/**
	 * Compares this object to the specified object.
	 */
	public boolean equals(Object obj)
	{
		if (obj == null || !(obj instanceof Long))
			return false;
		return ((Long) obj).value == value;
	}

	/**
	 * Returns an Long object holding the specified value. Calls to this
	 * method may be generated by the autoboxing feature.
	 */
	public static Long valueOf(long value)
	{
		return new Long(value);
	}

	/**
	 * Returns the value of this Long as a long.
	 */
	public long longValue()
	{
		return value;
	}

	/**
	 * Returns a String object representing this Long's value.
	 */
	public String toString()
	{
		// Duplicate code to String#valueOf(long)
		ScriptHelper.put("l", value, this);
		return (String) ScriptHelper.eval("String(l)", this);
	}

	public static long parseLong(String string) throws NumberFormatException
	{
		return Integer.parseInt(string);
	}

	public static String toString(long i)
	{
		return Integer.toString((int) i, 10);
	}

	public static String toString(long i, int radix)
	{
		return Integer.toString((int) i, radix);
	}
	
	public static int hashCode(long value)
	{
		return (int) (value ^ (value >>> 32));
	}
}
