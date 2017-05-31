package org.geepawhill.contentment.step;

import org.geepawhill.contentment.actor.Actor;
import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.OnFinished;
import org.geepawhill.contentment.jfx.JfxUtility;
import org.geepawhill.contentment.timing.Timing;

public class Entrance implements Step
{
	
	private Actor actor;

	public Entrance(Actor actor)
	{
		this.actor = actor;
	}

	@Override
	public void undo(Context context)
	{
		JfxUtility.removeIfNeeded(context,actor.group());
		context.actors.remove(actor);
	}

	@Override
	public void fast(Context context)
	{
		context.actors.add(actor);
		JfxUtility.addIfNeeded(context, actor.group());
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
		return Timing.INSTANT;
	}

}
