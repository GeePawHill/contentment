package org.geepawhill.contentment.core;

public class ParallelInterpolator implements ContextInterpolator
{

	private ContextInterpolator source;
	private ContextInterpolator after;

	public ParallelInterpolator(ContextInterpolator source, ContextInterpolator after)
	{
		this.source = source;
		this.after = after;
	}

	@Override
	public void interpolate(Context context, double fraction)
	{
		source.interpolate(context, fraction);
		after.interpolate(context, fraction);
	}

}
