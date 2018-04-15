package org.geepawhill.contentment.core;

import org.geepawhill.contentment.rhythm.Rhythm;

import javafx.scene.Group;

public class Context
{
	public final Group canvas;

	private Rhythm rhythm;

	public Context()
	{
		this.canvas = new Group();
	}
	
	public void add(GroupSource actor)
	{
		if (!canvas.getChildren().contains(actor.group()))
		{
			canvas.getChildren().add(actor.group());
		}
	}

	public void remove(GroupSource actor)
	{
		if (canvas.getChildren().contains(actor.group()))
		{
			canvas.getChildren().remove(actor.group());
		}
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
		canvas.getChildren().clear();
	}
}
