package org.geepawhill.contentment.step;

import org.geepawhill.contentment.actor.Actor;
import org.geepawhill.contentment.core.Context;

public class EntranceStep implements Fast
{

	private Actor actor;

	public EntranceStep(Actor actor)
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
