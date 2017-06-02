package org.geepawhill.contentment.core;

import java.util.ArrayList;

import org.geepawhill.contentment.step.Step;
import org.geepawhill.contentment.timing.Scheduler;


public class Sequence
{
	ArrayList<Step> steps;
	Scheduler scheduler;

	public Sequence()
	{
		steps = new ArrayList<>();
		scheduler = new Scheduler();
	}

	public Sequence(Step... steps)
	{
		this();
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
	
	public Sequence schedule(double ms)
	{
		scheduler.schedule(ms,steps);
		return this;
	}
	
	public double runTime()
	{
		double result = 0d;
		for(Step step : steps)
		{
			result += step.timing().ms();
		}
		return result;
	}
	
	public void dump()
	{
		for(Step step : steps)
		{
			System.out.println(step.timing()+" "+step);
		}
	}
}