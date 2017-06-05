package org.geepawhill.contentment.core;

import javafx.animation.Transition;
import javafx.util.Duration;

public class Animator extends Transition
{
	private ContextInterpolator interpolator;
	private Context context;
	
	public void play(Context context, OnFinished onFinished, double ms, ContextInterpolator interpolator)
	{
		this.context = context;
		this.interpolator = context.wrap(interpolator);
		if(ms<.1d) ms=.1d;
		setCycleDuration(Duration.millis(ms));
		setOnFinished(onFinished != null ? (event) -> onFinished.run() : null);
		play();
	}

	@Override
	protected void interpolate(double fraction)
	{
		interpolator.interpolate(context, fraction);
	}
}
