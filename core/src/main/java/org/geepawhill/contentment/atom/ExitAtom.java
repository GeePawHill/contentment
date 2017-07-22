package org.geepawhill.contentment.atom;

import org.geepawhill.contentment.actor.Actor;
import org.geepawhill.contentment.core.Atom;
import org.geepawhill.contentment.core.Context;

public class ExitAtom implements Atom
{
	private Actor actor;

	public ExitAtom(Actor actor)
	{
		this.actor = actor;
		
	}

	@Override
	public void setup(Context context)
	{
	}

	@Override
	public boolean partial(Context context, double fraction)
	{
		context.remove(actor);
		return false;
	}

}
