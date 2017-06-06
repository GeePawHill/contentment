package org.geepawhill.contentment.step;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.ContextInterpolator;

import javafx.animation.Transition;
import javafx.util.Duration;

public class ContextTransition extends Transition
{

	Context context;
	ContextInterpolator interpolator;

	public ContextTransition(Context context, ContextInterpolator interpolator, double ms)
	{
		this.context = context;
		this.interpolator = interpolator;
		setCycleDuration(Duration.millis(ms));
	}

	@Override
	protected void interpolate(double arg0)
	{
		interpolator.interpolate(context, arg0);
	}

}
