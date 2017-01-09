package org.geepawhill.contentment.newstep;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.model.OnFinished;
import org.geepawhill.contentment.model.Step;
import org.geepawhill.contentment.model.Timing;

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
	public void pause(Context context)
	{
	}

	@Override
	public void resume(Context context)
	{
	}

	@Override
	public Timing timing()
	{
		return Timing.INSTANT;
	}

}
