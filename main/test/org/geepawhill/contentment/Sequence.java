package org.geepawhill.contentment;

public class Sequence
{
	Step[] steps;

	public Sequence()
	{
		steps = new Step[0];
	}

	public Sequence(Step... steps)
	{
		this.steps = steps;
	}

	public int size()
	{
		return steps.length;
	}
}