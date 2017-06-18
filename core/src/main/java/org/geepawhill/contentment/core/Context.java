package org.geepawhill.contentment.core;

import org.geepawhill.contentment.actor.Actor;
import org.geepawhill.contentment.actor.Actors;
import org.geepawhill.contentment.rhythm.Rhythm;

import javafx.scene.Group;

public class Context
{

	public final Actors actors;
	public final Group canvas;
	private boolean skippingDelays;
	public final Rhythm rhythm;

	private ContextInterpolator after;

	public Context(Group canvas)
	{
		this.canvas = canvas;
		this.actors = new Actors();
		this.rhythm = new Rhythm();
		this.skipDelays(false);
	}
	
	public void skipDelays(boolean yesOrNo)
	{
		skippingDelays = yesOrNo;
	}

	public boolean isSkippingDelays()
	{
		return skippingDelays;
	}

	public void add(Actor actor)
	{
		actors.add(actor);
		if (!canvas.getChildren().contains(actor.group()))
		{
			canvas.getChildren().add(actor.group());
		}
	}

	public void remove(Actor actor)
	{
		if (canvas.getChildren().contains(actor.group()))
		{
			canvas.getChildren().remove(actor.group());
		}
		actors.remove(actor);
	}

	public void setAfter(ContextInterpolator source)
	{
		after = source;
	}

	public void clearAfter()
	{
		after = null;
	}

	public ContextInterpolator wrap(ContextInterpolator source)
	{
		if (after == null) return source;
		return new ParallelInterpolator(source, after);
	}

}
