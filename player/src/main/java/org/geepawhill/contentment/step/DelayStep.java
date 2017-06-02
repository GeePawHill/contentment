package org.geepawhill.contentment.step;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.OnFinished;
import org.geepawhill.contentment.timing.Timing;

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
	public void fast(Context context)
	{
	}

	@Override
	public void undo(Context context)
	{
	}

	@Override
	public void slow(Context context, OnFinished onFinished)
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
		return Timing.ms(ms);
	}

}
