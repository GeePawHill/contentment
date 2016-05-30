package org.geepawhill.contentment;

import java.util.ArrayList;
import java.util.Iterator;

public class Sequence implements Iterable<Step>
{
	ArrayList<Step> steps;

	public Sequence()
	{
		steps = new ArrayList<>();
	}

	public Sequence(Step... steps)
	{
		this.steps = new ArrayList<Step>();
		for(Step step : steps) this.steps.add(step);
	}

	public int size()
	{
		return steps.size();
	}

	@Override
	public Iterator<Step> iterator()
	{
		return steps.iterator();
	}

	public Step get(int index)
	{
		return steps.get(index);
	}
}