package org.geepawhill.contentment.step;

import java.util.ArrayList;

import org.geepawhill.contentment.core.*;

public class Chord implements Addable
{
	private final ArrayList<Gesture> playables;
	private OnFinished onFinished;
	private int finished;
	
	public Chord()
	{
		this.playables = new ArrayList<>();
	}

	public Chord add(Gesture Step)
	{
		playables.add(Step);
		return this;
	}
	
	@Override
	public void dump()
	{
		for(Gesture step : playables)
		{
			System.out.println(step);
		}
	}

	
	@Override
	public void fast(Context context)
	{
		for(Gesture Step : playables)
		{
			Step.fast(context);
		}
	}

	@Override
	public void slow(Context context, OnFinished onFinished)
	{
		this.onFinished = onFinished;
		this.finished = 0;
		for(Gesture Step : playables)
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
