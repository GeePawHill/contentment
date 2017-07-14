package org.geepawhill.contentment.atom;

import org.geepawhill.contentment.actor.Actor;
import org.geepawhill.contentment.core.Atom;
import org.geepawhill.contentment.core.Context;

public class EntranceAtom implements Atom
{
	
	private Actor actor;

	public EntranceAtom(Actor actor)
	{
		this.actor = actor;
	}

	@Override
	public void setup(Context context)
	{
	}

	@Override
	public void partial(Context context, double fraction)
	{
		context.add(actor);
	}

}
