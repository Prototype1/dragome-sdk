package com.dragome.compiler.utils;

public final class ImmutableEntry<T1, T2>
{

	private final T1 t1;
	private final T2 t2;

	public ImmutableEntry(T1 t1, T2 t2)
	{
		this.t1= t1;
		this.t2= t2;
	}

	public T1 getFirstValue()
	{
		return t1;
	}

	public T2 getSecondValue()
	{
		return t2;
	}

}
