package org.geepawhill.contentment;

import java.util.function.DoubleConsumer;

import javafx.animation.Transition;
import javafx.util.Duration;

class SimpleTransition extends Transition
{
	
	private DoubleConsumer interpolator;


	public SimpleTransition(double ms,DoubleConsumer interpolator)
	{
		this.interpolator = interpolator;
		setCycleDuration(Duration.millis(ms));
	}
	

	@Override
	protected void interpolate(double frac)
	{
		interpolator.accept(frac);
	}
	
}