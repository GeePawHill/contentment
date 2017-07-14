package org.geepawhill.contentment.atom;

import org.geepawhill.contentment.actor.Actor;
import org.geepawhill.contentment.actor.Actors;
import org.geepawhill.contentment.core.Atom;
import org.geepawhill.contentment.core.Context;

public class ClearAtom implements Atom
{
	
	public ClearAtom()
	{
		
	}

	@Override
	public void setup(Context context)
	{
	}

	@Override
	public void partial(Context context, double fraction)
	{
		Actors actors = new Actors();
		actors.addAll(context.actors);
		for (Actor actor : actors)
		{
			context.remove(actor);
		}
	}

}
