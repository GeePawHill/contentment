package org.geepawhill.contentment.step;

import java.util.ArrayList;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.OnFinished;
import org.geepawhill.contentment.timing.Scheduler;
import org.geepawhill.contentment.timing.Timing;

public class Timed implements Step
{
	private final Scheduler scheduler;
	private final ArrayList<Step> playables;
	private double ms;
	
	public Timed(double ms)
	{
		this.playables = new ArrayList<>();
		this.ms=ms;
		this.scheduler = new Scheduler();
	}

	public Timed add(Step Step)
	{
		playables.add(Step);
		return this;
	}
	
	public Timed add(Fast fast)
	{
		playables.add(new FastStep(fast));
		return this;
	}
	
	@Override
	public Timing timing()
	{
		schedule();
		return Timing.ms(ms);
	}
	
	private void schedule()
	{
		scheduler.schedule(ms, playables);
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
