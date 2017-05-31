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
	public void fast(Context context)
	{
		step.play(0d, context, OnFinished.NONE);
	}

	@Override
	public void undo(Context context)
	{
		step.unplay(context);
		
	}

	@Override
	public void slow(Context context, OnFinished onFinished)
	{
		step.play(step.timing().getAbsolute(), context, onFinished);
	}

	@Override
	public Timing timing()
	{
		return step.timing();
	}

}
