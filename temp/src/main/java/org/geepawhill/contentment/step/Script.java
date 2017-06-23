package org.geepawhill.contentment.step;

import java.util.ArrayList;

import org.geepawhill.contentment.timing.Scheduler;

public class Script
{
	ArrayList<SyncStep> steps;
	Scheduler scheduler;

	public Script()
	{
		steps = new ArrayList<>();
		scheduler = new Scheduler();
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