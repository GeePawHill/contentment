package org.geepawhill.contentment;

import java.util.ArrayList;
import java.util.Iterator;


public class Sequence implements Iterable<UnmarkedStep>
{
	ArrayList<UnmarkedStep> steps;

	public Sequence()
	{
		steps = new ArrayList<>();
	}

	public Sequence(UnmarkedStep... steps)
	{
		this.steps = new ArrayList<UnmarkedStep>();
		for(UnmarkedStep step : steps) this.steps.add(step);
	}

	public int size()
	{
		return steps.size();
	}

	@Override
	public Iterator<UnmarkedStep> iterator()
	{
		return steps.iterator();
	}

	public UnmarkedStep get(int index)
	{
		return steps.get(index);
	}
}