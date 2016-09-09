package org.geepawhill.contentment.step;

import org.geepawhill.contentment.core.Context;

import javafx.animation.Transition;
import javafx.util.Duration;

public class ContextTransition extends Transition
{
	
	Context context;
	ContextInterpolator interpolator;

	public ContextTransition(Context context,SubStep substep, double trueTime)
	{
		this.context = context;
		this.interpolator = substep.interpolator;
		setCycleDuration(Duration.millis(trueTime));
	}

	@Override
	protected void interpolate(double arg0)
	{
		interpolator.accept(arg0, context);
	}

}
