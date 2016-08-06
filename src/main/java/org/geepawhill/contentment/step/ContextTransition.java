package org.geepawhill.contentment.step;

import org.geepawhill.contentment.core.Context;

import javafx.animation.Transition;
import javafx.util.Duration;

public class ContextTransition extends Transition
{
	
	@FunctionalInterface
	public static interface Interpolator
	{
		void accept(double fraction, Context context);
	}

	Context context;
	Interpolator interpolator;

	public ContextTransition(Context context,double ms,Interpolator interpolator)
	{
		this.context = context;
		this.interpolator = interpolator;
		setCycleDuration(Duration.millis(ms));
	}

	@Override
	protected void interpolate(double arg0)
	{
		interpolator.accept(arg0, context);
	}

}
