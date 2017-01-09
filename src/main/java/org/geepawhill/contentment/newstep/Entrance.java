package org.geepawhill.contentment.newstep;

import org.geepawhill.contentment.core.Actor;
import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.jfx.JfxUtility;

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
		JfxUtility.removeIfNeeded(context,actor.group());
		context.actors.remove(actor);
	}

	@Override
	public void after(Context context)
	{
		context.actors.add(actor);
		JfxUtility.addIfNeeded(context, actor.group());
	}

}
