package org.geepawhill.contentment.step;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.model.Actor;
import org.geepawhill.contentment.model.OnFinished;
import org.geepawhill.contentment.model.Step;
import org.geepawhill.contentment.model.Timing;
import org.geepawhill.contentment.timing.FixedTiming;

import javafx.animation.Transition;

public class FadeStep implements Step
{

	private Actor actor;
	private double ms;
	private Transition transition;

	public FadeStep(Actor actor, double ms)
	{
		this.actor = actor;
		this.ms = ms;
	}

	@Override
	public void after(Context context)
	{
		doFade(1.0d, context);
	}

	@Override
	public void before(Context context)
	{
		doFade(0.0d, context);
	}

	@Override
	public void play(Context context, OnFinished onFinished)
	{
		transition = new ContextTransition(context, this::doFade, ms);
		transition.setOnFinished((event) -> onFinished.run());
		transition.play();
	}

	private void doFade(double fraction, Context context)
	{
		if (fraction <0.0001d)
		{
			actor.group().setOpacity(0d);
		}
		else
		{
			actor.group().setOpacity(fraction * 1d);
		}
	}

	@Override
	public Timing timing()
	{
		return new FixedTiming(ms);
	}

}
