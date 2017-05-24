package org.geepawhill.contentment.core;

import java.util.ArrayList;

import org.geepawhill.contentment.step.Instant;
import org.geepawhill.contentment.step.InstantStep;
import org.geepawhill.contentment.step.StopStep;


public class Sequence
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

	public Step get(int index)
	{
		return steps.get(index);
	}
	
	public void unmarked(Step step)
	{
		add(step);
	}
	
	public void unmarked(Instant instant)
	{
		add(instant);
	}
	
	public void add(Step step)
	{
		steps.add(step);
	}
	
	public void add(Instant instant)
	{
		steps.add(new InstantStep(instant));
	}
	
	public boolean isMarked(int index)
	{
		return steps.get(index) instanceof StopStep;
	}

	public double runTime()
	{
		double result = 0d;
		for(Step step : steps)
		{
			result += step.timing().getAbsolute();
		}
		return result;
	}
}