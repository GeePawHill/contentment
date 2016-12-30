package org.geepawhill.contentment.step;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.OnFinished;
import org.geepawhill.contentment.core.Step;
import org.geepawhill.contentment.timing.FixedTiming;
import org.geepawhill.contentment.timing.Timing;

import javafx.animation.Transition;
import javafx.util.Duration;

public class TransitionStep implements Step
{
	
	private Transition transition;
	
	@Override
	public Timing timing()
	{
		return new FixedTiming(transition.getTotalDuration().toMillis());
	}

	public TransitionStep(Transition transition)
	{
		this.transition = transition;
		
	}

	@Override
	public void after(Context context)
	{
		transition.jumpTo(Duration.millis(10000d));
	}

	@Override
	public void before(Context context)
	{
		transition.jumpTo(Duration.millis(0));
	}

	@Override
	public void play(Context context, OnFinished onFinished)
	{
		transition.setOnFinished((event) -> onFinished.run());
		transition.play();
	}

	@Override
	public void pause(Context context)
	{
		transition.pause();
	}

	@Override
	public void resume(Context context)
	{
		transition.play();
	}

}
