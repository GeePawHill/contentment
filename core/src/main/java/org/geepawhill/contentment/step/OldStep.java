package org.geepawhill.contentment.step;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.OnFinished;
import org.geepawhill.contentment.timing.Timing;

public class OldStep implements Step
{
	private NewStep step;

	public OldStep(NewStep step)
	{
		this.step = step;
	}

	@Override
	public void after(Context context)
	{
		step.play(0d, context, NO_FINISH);
	}

	@Override
	public void unplay(Context context)
	{
		step.unplay(context);
		
	}

	@Override
	public void play(Context context, OnFinished onFinished)
	{
		step.play(step.timing().getAbsolute(), context, onFinished);
	}

	@Override
	public Timing timing()
	{
		return step.timing();
	}

}
