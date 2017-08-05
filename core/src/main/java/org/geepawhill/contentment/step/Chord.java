package org.geepawhill.contentment.step;

import java.util.ArrayList;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.OnFinished;

public class Chord implements Addable
{
	private final ArrayList<Step> playables;
	private OnFinished onFinished;
	private int finished;
	
	public Chord()
	{
		this.playables = new ArrayList<>();
	}

	public Chord add(Step Step)
	{
		playables.add(Step);
		return this;
	}
	
	@Override
	public void dump()
	{
		for(Step step : playables)
		{
			System.out.println(step);
		}
	}

	
	@Override
	public void fast(Context context)
	{
		for(Step Step : playables)
		{
			Step.fast(context);
		}
	}

	@Override
	public void slow(Context context, OnFinished onFinished)
	{
		this.onFinished = onFinished;
		this.finished = 0;
		for(Step Step : playables)
		{
			Step.slow(context, ()->next());
		}
	}
	
	private void next()
	{
		finished+=1;
		if(finished==playables.size()) onFinished.run();
	}
	
}
