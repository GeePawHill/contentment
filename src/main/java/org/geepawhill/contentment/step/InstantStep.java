package org.geepawhill.contentment.step;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.OnFinished;
import org.geepawhill.contentment.core.Step;

public class InstantStep implements Step
{
	private Step source;

	public InstantStep(Step source)
	{
		this.source = source;
		
	}

	@Override
	public void after(Context context)
	{
		source.after(context);
	}

	@Override
	public void before(Context context)
	{
		source.before(context);
	}

	@Override
	public void play(Context context, OnFinished onFinished)
	{
		after(context);
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

}