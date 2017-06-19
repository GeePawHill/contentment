package org.geepawhill.contentment.step;

import java.util.ArrayList;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.OnFinished;
import org.geepawhill.contentment.timing.Timing;

public class Chord implements Step
{
	private final ArrayList<Step> playables;
	private long ms;
	private OnFinished onFinished;
	private int finished;
	
	public Chord()
	{
		this.playables = new ArrayList<>();
		this.ms=0L;
	}

	public void add(Step Step)
	{
		playables.add(Step);
		if(Step.timing().ms()>ms) ms = (long)Step.timing().ms();
	}

	@Override
	public Timing timing()
	{
		return Timing.ms(ms);
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
	public void undo(Context context)
	{
		for(int i= playables.size()-1; i>=0;i--)
		{
			playables.get(i).undo(context);
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
