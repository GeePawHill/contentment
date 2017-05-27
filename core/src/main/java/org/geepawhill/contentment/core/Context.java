package org.geepawhill.contentment.core;

import org.geepawhill.contentment.actor.Actors;

import javafx.scene.Group;

public class Context
{

	public final Actors actors;
	public final Group canvas;
	public boolean skipKeyframes;

	public Context(Group canvas)
	{
		this.canvas = canvas;
		this.actors = new Actors();
		this.skipKeyframes = false;
	}

}
