package com.dragome.compiler.parser.advanced;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Subject of change. An class view for dragome carries everything the compiler should know to generate javascript out of it 
 * @author Mo
 *
 */

public class ClassView
{
	private final String className;
	private final Path path;

	private ClassView dependsOn;
	private List<ClassView> implementsInterfaces= new ArrayList<>();
	private ClassView extendsClass;
	
	private List<ClassView> instanceVars; 
	//TODO change object to something meaningful 
	private List<Object> methods; 
	private List<Object> constructors; 
	

	public ClassView(String className, Path path)
	{
		if (className == null || path == null || !Files.exists(path))
			throw new IllegalArgumentException("ClassView could not be created");

		this.path= path;
		this.className= className;
	}
	
	public boolean hasInstanceVars(){
		return !instanceVars.isEmpty();
	}
	
	public boolean hasMethod(){
		return !methods.isEmpty();
	}
	
	public boolean implementsInterface(){
		return !implementsInterfaces.isEmpty();
	}

}
