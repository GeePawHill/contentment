package org.geepawhill.contentment.step;

import java.util.ArrayList;

import org.geepawhill.contentment.core.*;

public class Chord implements Addable
{
	private final ArrayList<Gesture> gestures;
	private OnFinished onFinished;
	private int finished;
	
	public Chord()
	{
		this.gestures = new ArrayList<>();
	}

	public Chord add(Gesture gesture)
	{
		gestures.add(gesture);
		return this;
	}
	
	@Override
	public void dump()
	{
		for(Gesture step : gestures)
		{
			System.out.println(step);
		}
	}

	
	@Override
	public void fast(Context context)
	{
		for(Gesture Step : gestures)
		{
			Step.fast(context);
		}
	}

	@Override
	public void slow(Context context, OnFinished onFinished)
	{
		this.onFinished = onFinished;
		this.finished = 0;
		for(Gesture Step : gestures)
		{
			Step.slow(context, ()->next());
		}
	}
	
	private void next()
	{
		finished+=1;
		if(finished==gestures.size()) onFinished.run();
	}
	
}
