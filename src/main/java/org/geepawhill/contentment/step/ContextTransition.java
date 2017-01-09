package org.geepawhill.contentment.step;

import org.geepawhill.contentment.core.Context;

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

	public ContextTransition(Context context,SubStep substep, double ms)
	{
		this(context,substep.interpolator,ms);
	}

	@Override
	protected void interpolate(double arg0)
	{
		interpolator.accept(arg0, context);
	}

}
