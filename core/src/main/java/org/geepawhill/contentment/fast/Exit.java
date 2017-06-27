package org.geepawhill.contentment.fast;

import org.geepawhill.contentment.actor.Actor;
import org.geepawhill.contentment.core.Context;

public class Exit implements Fast
{

	private Actor actor;

	public Exit(Actor actor)
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
}
