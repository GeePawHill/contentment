package org.geepawhill.contentment.step;

import java.util.function.Consumer;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.OnFinished;
import org.geepawhill.contentment.core.Step;
import org.geepawhill.contentment.timing.FixedTiming;
import org.geepawhill.contentment.timing.Timing;

public class OneWayStep implements Step
{
	
	private Consumer<Context> action;

	public OneWayStep(Consumer<Context> action)
	{
		this.action = action;
	}

	@Override
	public void after(Context context)
	{
		action.accept(context);
	}

	@Override
	public void before(Context context)
	{
	}

	@Override
	public void play(Context context, OnFinished onFinished)
	{
		after(context);
		onFinished.run();
		
	}

	@Override
	public Timing timing()
	{
		return FixedTiming.INSTANT;
	}
}
