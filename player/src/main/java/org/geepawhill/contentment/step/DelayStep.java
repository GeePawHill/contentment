package org.geepawhill.contentment.step;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.model.OnFinished;
import org.geepawhill.contentment.model.Step;
import org.geepawhill.contentment.model.Timing;
import org.geepawhill.contentment.timing.FixedTiming;

import javafx.animation.Transition;
import javafx.util.Duration;

public class DelayStep implements Step
{
	
	private double ms;
	Transition transition;
	
	static class NoOpTransition extends Transition
	{
		public NoOpTransition(double ms,OnFinished onFinished)
		{
			setCycleDuration(Duration.millis(ms));
			setOnFinished( event -> onFinished.run() );
		}

		@Override
		protected void interpolate(double frac)
		{
		}
		
	}

	public DelayStep(double ms)
	{
		this.ms = ms;
	}

	@Override
	public void after(Context context)
	{
	}

	@Override
	public void before(Context context)
	{
	}

	@Override
	public void play(Context context, OnFinished onFinished)
	{
		if(context.skipKeyframes==true)
		{
			onFinished.run();
			return;
		}
		new NoOpTransition(ms,onFinished).play();
	}

	@Override
	public Timing timing()
	{
		return new FixedTiming(ms);
	}

}
