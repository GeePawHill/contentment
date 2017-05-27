package org.geepawhill.contentment.step;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.OnFinished;
import org.geepawhill.contentment.timing.FixedTiming;
import org.geepawhill.contentment.timing.Timing;

public class StopStep implements Step
{

	@Override
	public void after(Context context)
	{
	}

	@Override
	public void unplay(Context context)
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
