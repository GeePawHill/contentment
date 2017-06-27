package org.geepawhill.contentment.core;

import java.util.ArrayList;

import org.geepawhill.contentment.step.SyncStep;

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

	public Script add(SyncStep step)
	{
		steps.add(step);
		return this;
	}
}