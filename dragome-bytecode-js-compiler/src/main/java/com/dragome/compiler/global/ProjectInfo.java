package com.dragome.compiler.global;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import com.dragome.compiler.interfaces.Filterable;
import com.dragome.compiler.utils.ClassFilterer;
import com.dragome.compiler.utils.ImmutableEntry;

public enum ProjectInfo
{

	ACCESS;

	private final Collection<ImmutableEntry<Class<?>, File>> allClasses;
	private final Collection<ImmutableEntry<Class<?>, File>> filteredClasses;
	private final Filterable<ImmutableEntry<Class<?>, File>> classFilter;

	private ProjectInfo()
	{
		allClasses= new ArrayList<>();
		filteredClasses= new ArrayList<>();

		classFilter= new ClassFilterer();

		for (ImmutableEntry<Class<?>, File> entry : allClasses)
		{
			if (classFilter.isValid(entry))
				filteredClasses.add(entry);
		}

	}

	public Collection<ImmutableEntry<Class<?>, File>> getAllClasses()
	{
		return allClasses;
	}

	public Collection<ImmutableEntry<Class<?>, File>> getFilteredClasses()
	{
		return filteredClasses;
	}

	public Filterable<ImmutableEntry<Class<?>, File>> getClassFilter()
	{
		return classFilter;
	}

}
