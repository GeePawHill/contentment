package org.geepawhill.contentment.core;

import org.geepawhill.contentment.actor.Actor;
import org.geepawhill.contentment.actor.Actors;

import javafx.scene.Group;

public class Context
{
	public final Actors actors;
	public final Group canvas;

	private ContextInterpolator extra;

	public Context()
	{
		this.canvas = new Group();
		this.actors = new Actors();
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

	public void setExtra(ContextInterpolator extra)
	{
		this.extra = extra;
	}

	public ContextInterpolator wrap(ContextInterpolator source)
	{
		if (extra == null) return source;
		return new ParallelInterpolator(source, extra);
	}
}
