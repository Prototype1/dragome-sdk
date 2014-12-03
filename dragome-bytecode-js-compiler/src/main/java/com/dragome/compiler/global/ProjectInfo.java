package com.dragome.compiler.global;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.WildcardFileFilter;

import com.dragome.compiler.interfaces.Filterable;
import com.dragome.compiler.utils.ClassFilterer;
import com.dragome.compiler.utils.ImmutableEntry;

public class ProjectInfo
{

	private final Collection<ImmutableEntry<Class<?>, File>> allClasses;
	private final Collection<ImmutableEntry<Class<?>, File>> filteredClasses;
	private final Collection<JarFile> jars;
	private final Filterable<ImmutableEntry<Class<?>, File>> classFilter;

	private final File workingDir;

	private ProjectInfo(File workingDir)
	{
		this.workingDir= workingDir;

		if (workingDir == null || !workingDir.exists())
			throw new RuntimeException("Working Dir seems invalid");

		allClasses= new ArrayList<>();
		filteredClasses= new ArrayList<>();
		jars= new ArrayList<>();
		classFilter= new ClassFilterer();

		getJars();

	}

	private void getJars()
	{

		for (File currJar : FileUtils.listFiles(workingDir, new WildcardFileFilter("*.jar"), DirectoryFileFilter.DIRECTORY))
		{
			try
			{
				jars.add(new JarFile(currJar));
			}
			catch (IOException e)
			{

				e.printStackTrace();
			}
		}

	}
	//
	//	private void getClasses(){
	//		Collection<File> allClasses = FileUtils.listFiles(workingDir, new WildcardFileFilter("*.class"), DirectoryFileFilter.DIRECTORY); 
	//		
	//		for (JarFile currJar : jars)
	//		{
	//			allClasses.addAll(findClassesInJar(currJar));
	//		}
	//		
	//		for (File file : allClasses)
	//		{
	//			ClassLoader.getSystemClassLoader(Files.)
	//		}
	//	}
	private void filterClasses()
	{
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

	private static List<String> findClassesInJar(JarFile jarFile)
	{
		ArrayList<String> result= new ArrayList<String>();

		final Enumeration<JarEntry> entries= jarFile.entries();
		while (entries.hasMoreElements())
		{
			final JarEntry entry= entries.nextElement();
			final String entryName= entry.getName();
			if (entryName.endsWith(".class"))
				result.add(entryName.replace('/', File.separatorChar).replace(".class", ""));
		}

		return result;
	}

}
