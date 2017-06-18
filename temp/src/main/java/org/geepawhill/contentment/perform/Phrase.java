package org.geepawhill.contentment.perform;

import java.util.ArrayList;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.OnFinished;

public class Phrase implements Playable
{
	private final ArrayList<Playable> playables;
	private long ms;
	
	public Phrase()
	{
		this.playables = new ArrayList<>();
		this.ms=0L;
	}

	public void add(Playable playable)
	{
		playables.add(playable);
		ms+=playable.ms();
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
		new SlowPlayer(context,onFinished,playables);
	}
	
}
