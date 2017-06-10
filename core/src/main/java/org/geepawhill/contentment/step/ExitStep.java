package org.geepawhill.contentment.step;

import org.geepawhill.contentment.actor.Actor;
import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.OnFinished;
import org.geepawhill.contentment.timing.Timing;

public class ExitStep implements Step
{

	private Actor actor;

	public ExitStep(Actor actor)
	{
		this.actor = actor;
	}

	@Override
	public void undo(Context context)
	{
		context.add(actor);
	}

	@Override
	public String toString()
	{
		return "Exit: " + actor.nickname();
	}

	@Override
	public void fast(Context context)
	{
		context.remove(actor);
	}

	@Override
	public void slow(Context context, OnFinished onFinished)
	{
		fast(context);
		onFinished.run();
	}

	@Override
	public Timing timing()
	{
		return Timing.instant();
	}

}
