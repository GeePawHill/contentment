package org.geepawhill.contentment.core;

import org.geepawhill.contentment.actor.Actor;
import org.geepawhill.contentment.actor.Actors;
import org.geepawhill.contentment.rhythm.Rhythm;

import javafx.scene.Group;

public class Context
{
	public final Actors actors;
	public final Group canvas;

	private Rhythm rhythm;

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

	public void setRhythm(Rhythm rhythm)
	{
		this.rhythm = rhythm;
	}
	
	public long beat()
	{
		return rhythm.beat();
	}

	public void wipe()
	{
		Actors local = new Actors();
		local.addAll(actors);
		for(Actor actor : local)
		{
			remove(actor);
		}
	}
}
