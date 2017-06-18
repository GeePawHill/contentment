package org.geepawhill.contentment.perform;

import java.util.ArrayList;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.OnFinished;

public class Chord implements Playable
{
	private final ArrayList<Playable> playables;
	private long ms;
	private OnFinished onFinished;
	private int finished;
	
	public Chord()
	{
		this.playables = new ArrayList<>();
		this.ms=0L;
	}

	public void add(Playable playable)
	{
		playables.add(playable);
		if(playable.ms()>ms) ms = playable.ms();
	}

	@Override
	public long ms()
	{
		return ms;
	}

	@Override
	public void fast(Context context)
	{
		for(Playable playable : playables)
		{
			playable.fast(context);
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
		for(Playable playable : playables)
		{
			playable.slow(context, ()->next());
		}
	}
	
	private void next()
	{
		finished+=1;
		if(finished==playables.size()) onFinished.run();
	}
	
}
