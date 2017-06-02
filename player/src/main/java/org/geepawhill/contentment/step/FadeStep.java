package org.geepawhill.contentment.step;

import org.geepawhill.contentment.actor.Actor;
import org.geepawhill.contentment.core.Animator;
import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.OnFinished;
import org.geepawhill.contentment.timing.Timing;

public class FadeStep implements Step
{

	private Actor actor;
	private Animator animator;
	private Timing timing;

	public FadeStep(Actor actor, double ms)
	{
		this.actor = actor;
		this.animator = new Animator();
		this.timing = Timing.ms(ms);
	}

	@Override
	public void fast(Context context)
	{
		doFade(context, 1.0d);
	}

	@Override
	public void undo(Context context)
	{
		doFade(context, 0.0d);
	}

	@Override
	public void slow(Context context, OnFinished onFinished)
	{
		animator.play(context,onFinished,timing.ms(),this::doFade);
	}

	private void doFade(Context context, double fraction)
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
		return timing;
	}
	
	public String toString()
	{
		return "FadeStep: "+actor.nickname();
	}

}
