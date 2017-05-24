package org.geepawhill.contentment.step;

import java.util.function.Consumer;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.model.OnFinished;
import org.geepawhill.contentment.model.Step;
import org.geepawhill.contentment.model.Timing;
import org.geepawhill.contentment.timing.FixedTiming;

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
