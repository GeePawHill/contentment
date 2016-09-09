package org.geepawhill.contentment.step;

public class SubStep
{
	public final double proportion;
	public final ContextInterpolator interpolator;

	public SubStep(double proportion,ContextInterpolator interpolator)
	{
		this.proportion = proportion;
		this.interpolator = interpolator;
	}
}
