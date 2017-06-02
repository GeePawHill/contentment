package org.geepawhill.contentment.step;

import org.geepawhill.contentment.actor.Actor;
import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.OnFinished;
import org.geepawhill.contentment.timing.Timing;
import org.geepawhill.contentment.utility.JfxUtility;

public class EntranceStep implements Step
{
	
	private Actor actor;

	public EntranceStep(Actor actor)
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
	public String toString()
	{
		return "Entrance: "+actor.nickname();
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
		return Timing.instant();
	}

}
