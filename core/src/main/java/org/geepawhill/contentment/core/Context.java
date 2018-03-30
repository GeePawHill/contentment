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
		if (!canvas.getChildren().contains(actor.get()))
		{
			canvas.getChildren().add(actor.get());
		}
	}

	public void remove(GroupSource actor)
	{
		if (canvas.getChildren().contains(actor.get()))
		{
			canvas.getChildren().remove(actor.get());
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
