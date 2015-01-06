package dragome.compiler.parser.model;

import java.util.Map;

import com.google.common.collect.Maps;

public class ClassVars
{
	private final Map<String, Variable> instanceVars= Maps.newHashMap();
	private final Map<String, Variable> staticVars= Maps.newHashMap();

	public ClassVars()
	{

	}

	public void putVar(String name, Variable var, VarAccess choice)
	{
		if (choice == VarAccess.STATIC)
			staticVars.put(name, var);
		else
			instanceVars.put(name, var);
	}

	public Variable getVar(String name, VarAccess choice)
	{
		if (choice == VarAccess.STATIC)
			return staticVars.get(name);
		else
			return instanceVars.get(name);
	}

	public enum VarAccess
	{
		STATIC, INSTANCE;
	}

}
