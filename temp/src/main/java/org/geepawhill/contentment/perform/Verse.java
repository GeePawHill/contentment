package org.geepawhill.contentment.perform;

import java.util.ArrayList;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.OnFinished;

public class Verse
{
	private final ArrayList<Playable> playables;
	private long ms;
	
	public Verse()
	{
		this.playables = new ArrayList<>();
		this.ms=0L;
	}

	public void add(Playable playable)
	{
		playables.add(playable);
		ms+=playable.ms();
	}

	public long ms()
	{
		return ms;
	}

	public void fast(Context context)
	{
		for(Playable playable : playables)
		{
			playable.fast(context);
		}
	}

	public void undo(Context context)
	{
		for(int i= playables.size()-1; i>=0;i--)
		{
			playables.get(i).undo(context);
		}
	}
	
	static class SlowPlayer
	{
		private int current;
		private OnFinished onFinished;
		private ArrayList<Playable> playables;
		private Context context;
		
		public SlowPlayer(Context context, OnFinished onFinished, ArrayList<Playable> playables)
		{
			this.context = context;
			this.onFinished = onFinished;
			this.playables = playables;
			this.current = 0;
			playables.get(current).slow(context, () -> next());
		}
		
		private void next()
		{
			current+=1;
			if(current==playables.size()) onFinished.run();
			else playables.get(current).slow(context, () -> next());
		}
	}
	
	public void slow(Context context, OnFinished onFinished)
	{
		new SlowPlayer(context,onFinished,playables);
	}
	
}
