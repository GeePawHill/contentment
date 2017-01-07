package org.geepawhill.contentment.newstep;

import org.geepawhill.contentment.core.Actor;
import org.geepawhill.contentment.core.Context;

public class Entrance implements Instant
{
	
	private Actor actor;

	public Entrance(Actor actor)
	{
		this.actor = actor;
	}

	@Override
	public void before(Context context)
	{
		context.actors.remove(actor);
	}

	@Override
	public void after(Context context)
	{
		context.actors.add(actor);
	}

}
