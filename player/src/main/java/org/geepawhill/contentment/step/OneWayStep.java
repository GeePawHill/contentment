package org.geepawhill.contentment.step;

import java.util.function.Consumer;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.OnFinished;
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
	public void instant(Context context)
	{
		action.accept(context);
	}

	@Override
	public void undo(Context context)
	{
	}

	@Override
	public void slow(Context context, OnFinished onFinished)
	{
		instant(context);
		onFinished.run();
		
	}

	@Override
	public Timing timing()
	{
		return FixedTiming.INSTANT;
	}
}
