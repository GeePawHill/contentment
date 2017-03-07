package org.geepawhill.contentment.core;

import java.util.ArrayList;

import org.geepawhill.contentment.model.Step;
import org.geepawhill.contentment.newstep.Instant;
import org.geepawhill.contentment.newstep.InstantStep;
import org.geepawhill.contentment.newstep.Stop;


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
		return steps.get(index) instanceof Stop;
	}
}