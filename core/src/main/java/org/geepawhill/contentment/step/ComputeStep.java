package org.geepawhill.contentment.step;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.ContextInterpolator;
import org.geepawhill.contentment.core.OnFinished;
import org.geepawhill.contentment.timing.Timing;

public class ComputeStep implements Step
{

	private ContextInterpolator interpolator;

	public ComputeStep(ContextInterpolator interpolator)
	{
		this.interpolator = interpolator;

	}

	@Override
	public void slow(Context context, OnFinished onFinished)
	{
		fast(context);
		onFinished.run();
	}

	@Override
	public void fast(Context context)
	{
		interpolator.interpolate(context, 1d);
	}

	@Override
	public void undo(Context context)
	{
	}

	@Override
	public Timing timing()
	{
		return Timing.instant();
	}

}
