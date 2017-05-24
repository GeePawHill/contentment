package org.geepawhill.contentment.step;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.model.OnFinished;
import org.geepawhill.contentment.model.Step;
import org.geepawhill.contentment.model.Timing;
import org.geepawhill.contentment.timing.FixedTiming;

public class StopStep implements Step
{

	@Override
	public void after(Context context)
	{
	}

	@Override
	public void before(Context context)
	{
	}

	@Override
	public void play(Context context, OnFinished onFinished)
	{
		onFinished.run();
	}

	@Override
	public Timing timing()
	{
		return FixedTiming.INSTANT;
	}

}
