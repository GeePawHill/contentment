package org.geepawhill.contentment.step;

import java.util.ArrayList;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.OnFinished;
import org.geepawhill.contentment.timing.Timing;

public class Phrase implements Step
{
	private final ArrayList<Step> playables;
	private long ms;
	
	public Phrase()
	{
		this.playables = new ArrayList<>();
		this.ms=0L;
	}

	public void add(Step Step)
	{
		playables.add(Step);
		ms+=Step.timing().ms();
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
		new SlowPlayer(context,onFinished,playables);
	}
	
}
