package org.geepawhill.contentment.core;

import org.geepawhill.contentment.outline.KvOutline;

import javafx.scene.Group;

public class Context
{

	public final Actors actors;
	public final Group canvas;

	public Context(Group canvas)
	{
		this.canvas = canvas;
		this.actors = new Actors();
	}

	public void outline(KvOutline output)
	{
		actors.outline(output);
	}

	public KvOutline outline()
	{
		KvOutline output = new KvOutline();
		outline(output);
		return output;
	}
}
