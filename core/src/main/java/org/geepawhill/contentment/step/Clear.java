package org.geepawhill.contentment.step;

import org.geepawhill.contentment.actor.Actor;
import org.geepawhill.contentment.actor.Actors;
import org.geepawhill.contentment.core.Context;

public class Clear implements Fast
{

	private Actors actors = new Actors();

	public Clear()
	{
		actors = new Actors();
	}

	@Override
	public void fast(Context context)
	{
		actors.clear();
		actors.addAll(context.actors);
		for (Actor actor : actors)
		{
			context.remove(actor);
		}
	}

	@Override
	public void undo(Context context)
	{
		for (Actor actor : actors)
		{
			context.add(actor);
		}
		actors.clear();
	}

	@Override
	public String toString()
	{
		return "Clear";
	}

}
