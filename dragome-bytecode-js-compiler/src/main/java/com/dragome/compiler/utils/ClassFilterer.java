package com.dragome.compiler.utils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.dragome.commons.compiler.annotations.ServerOnly;
import com.dragome.compiler.interfaces.Filterable;

public class ClassFilterer implements Filterable<ImmutableEntry<Class<?>, File>>
{

	/**
	 * Map which containes every checked class and if the class has an annotation 
	 * which prevents it from being parsed / compiled
	 */
	private final Map<String, Boolean> cachedCheckedClasses= new HashMap<>();

	@Override
	public boolean isValid(ImmutableEntry<Class<?>, File> type)
	{

		String pathname= type.getSecondValue().getAbsolutePath();

		boolean valid= !hasInvalidAnnotation(type.getFirstValue());
		valid&= !pathname.toString().contains(File.separator + "serverside");

		return valid;
	}
	public boolean hasInvalidAnnotation(Class<?> clazz)
	{

		Class<?> currClazz= clazz;
		String currName= clazz.getName();

		Boolean cachedBool= false;

		do
		{
			if ((cachedBool= cachedCheckedClasses.get(currName)) != null && cachedBool)
			{
				return true;
			}
			else
			{
				cachedBool= currClazz.getAnnotation(ServerOnly.class) != null;
				cachedCheckedClasses.put(currName, cachedBool);

				if (cachedBool)
					return true;

			}

			currClazz= currClazz.getSuperclass();
			currName= clazz.getName();

		}
		while (currClazz != null);

		return false;
	}
}
