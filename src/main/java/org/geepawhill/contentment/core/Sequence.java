package org.geepawhill.contentment.core;

import java.util.ArrayList;


public class Sequence
{
	static class StepAndMark
	{
		public final boolean isMarked;
		public final Step step;
		
		public StepAndMark(boolean isMarked,Step step)
		{
			this.isMarked = isMarked;
			this.step = step;
		}
	}
	ArrayList<StepAndMark> steps;

	public Sequence()
	{
		steps = new ArrayList<>();
	}

	public Sequence(Step... steps)
	{
		this.steps = new ArrayList<StepAndMark>();
		for(Step step : steps) this.steps.add(new StepAndMark(true,step));
	}

	public int size()
	{
		return steps.size();
	}

	public Step get(int index)
	{
		return steps.get(index).step;
	}
	
	public void marked(Step step)
	{
		add(true,step);
	}
	
	public void unmarked(Step step)
	{
		add(false,step);
	}
	
	public void add(boolean isMarked,Step step)
	{
		steps.add(new StepAndMark(isMarked,step));
	}
	
	public boolean isMarked(int index)
	{
		return steps.get(index).isMarked;
	}

	
}