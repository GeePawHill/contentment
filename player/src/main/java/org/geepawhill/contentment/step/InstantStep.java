package org.geepawhill.contentment.step;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.OnFinished;
import org.geepawhill.contentment.core.Step;
import org.geepawhill.contentment.timing.Timing;

public class InstantStep implements Step
{
	private Instant instant;

	public InstantStep(Instant instant)
	{
		this.instant = instant;
	}

	@Override
	public void after(Context context)
	{
		instant.after(context);
	}

	@Override
	public void before(Context context)
	{
		instant.before(context);
	}

	@Override
	public void play(Context context, OnFinished onFinished)
	{
		instant.after(context);
		onFinished.run();
	}

	@Override
	public Timing timing()
	{
		return Timing.INSTANT;
	}

}
