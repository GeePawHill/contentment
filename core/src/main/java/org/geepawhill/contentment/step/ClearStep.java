package org.geepawhill.contentment.step;

import org.geepawhill.contentment.actor.Actor;
import org.geepawhill.contentment.actor.Actors;
import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.OnFinished;
import org.geepawhill.contentment.timing.Timing;

public class ClearStep implements Step
{
	
	private Actors actors = new Actors();

	public ClearStep()
	{
		actors = new Actors();
	}
	
	@Override
	public Timing timing()
	{
		return Timing.instant();
	}

	@Override
	public void fast(Context context)
	{
		actors.clear();
		actors.addAll(context.actors);
		for(Actor actor : actors)
		{
			context.remove(actor);
		}
	}

	@Override
	public void undo(Context context)
	{
		for(Actor actor : actors)
		{
			context.add(actor);
		}
		actors.clear();
	}

	@Override
	public void slow(Context context, OnFinished onFinished)
	{
		fast(context);
		onFinished.run();
	}
	
	@Override
	public String toString()
	{
		return "Clear";
	}

}