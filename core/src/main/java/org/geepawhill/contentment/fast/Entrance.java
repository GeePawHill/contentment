package org.geepawhill.contentment.fast;

import org.geepawhill.contentment.actor.Actor;
import org.geepawhill.contentment.core.Context;

public class Entrance implements Fast
{

	private Actor actor;

	public Entrance(Actor actor)
	{
		this.actor = actor;
	}

	@Override
	public void undo(Context context)
	{
		context.remove(actor);
	}

	@Override
	public String toString()
	{
		return "Entrance: " + actor.nickname();
	}

	@Override
	public void fast(Context context)
	{
		context.add(actor);
	}
}
