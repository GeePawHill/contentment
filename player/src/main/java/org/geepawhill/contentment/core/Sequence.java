package org.geepawhill.contentment.core;

import java.util.ArrayList;

import org.geepawhill.contentment.step.Instant;
import org.geepawhill.contentment.step.InstantStep;


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
	
	public void add(Sequence sequence)
	{
		steps.addAll(sequence.steps);
	}
	
	public void add(Step step)
	{
		steps.add(step);
	}
	
	public void add(Instant instant)
	{
		steps.add(new InstantStep(instant));
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