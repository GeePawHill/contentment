package org.geepawhill.contentment.step;

import java.util.ArrayList;

public class Script
{
	ArrayList<SyncStep> steps;

	public Script()
	{
		steps = new ArrayList<>();
	}

	public int size()
	{
		return steps.size();
	}

	public SyncStep get(int index)
	{
		return steps.get(index);
	}

	public SyncStep last()
	{
		return steps.get(size() - 1);
	}

	public Script add(SyncStep step)
	{
		steps.add(step);
		return this;
	}
}