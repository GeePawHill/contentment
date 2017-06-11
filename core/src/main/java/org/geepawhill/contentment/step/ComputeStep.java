package org.geepawhill.contentment.step;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.ContextInterpolator;

public class ComputeStep implements Fast
{

	private ContextInterpolator interpolator;

	public ComputeStep(ContextInterpolator interpolator)
	{
		this.interpolator = interpolator;

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
}
