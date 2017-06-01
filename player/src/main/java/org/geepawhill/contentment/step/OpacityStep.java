package org.geepawhill.contentment.step;

import org.geepawhill.contentment.actor.Actor;
import org.geepawhill.contentment.core.Animator;
import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.OnFinished;
import org.geepawhill.contentment.timing.FixedTiming;
import org.geepawhill.contentment.timing.Timing;

public class OpacityStep implements Step
{
	private Timing timing;
	private Actor actor;
	private double opacity;
	private double oldOpacity;

	public OpacityStep(double ms, Actor actor, double opacity)
	{
		this.timing = new FixedTiming(ms);
		this.actor = actor;
		this.opacity = opacity;
	}

	@Override
	public void slow(Context context, OnFinished onFinished)
	{
		oldOpacity = actor.group().getOpacity();
		new Animator().play(context,onFinished,timing.getAbsolute(),this::interpolate);
	}

	public void interpolate(Context context, double fraction)
	{
		double newOpacity = fraction * (oldOpacity+(opacity-oldOpacity));
		actor.group().setOpacity(newOpacity);
	}

	@Override
	public void fast(Context context)
	{
		oldOpacity = actor.group().getOpacity();
		interpolate(context,1d);
	}

	@Override
	public void undo(Context context)
	{
		actor.group().setOpacity(oldOpacity);
	}

	@Override
	public Timing timing()
	{
		return timing;
	}

}
