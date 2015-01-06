package dragome.compiler.parser;

import dragome.compiler.model.method.MethodHolder;
import dragome.compiler.model.values.ClassValue;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;

public final class DependenciesTree
{

	private Graph<ClassValue, MethodHolder> dependencies= new SparseMultigraph<>();

	public synchronized void addClass(ClassValue value)
	{
		if (value != null)
			dependencies.addVertex(value);
	}

	public void addDependencie(ClassValue owner, MethodHolder context, ClassValue dependsOn)
	{
		if (!check(owner, dependsOn))
			return;
		synchronized (dependencies)
		{
			dependencies.addEdge(context, owner, dependsOn);
		}
		
	}
	private boolean check(ClassValue... values)
	{

		for (ClassValue classValue : values)
		{
			if (classValue == null)
				return false;
			if (!dependencies.containsVertex(classValue))
				addClass(classValue);
		}
		return true;
	}

}
