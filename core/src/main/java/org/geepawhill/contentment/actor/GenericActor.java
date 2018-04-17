package org.geepawhill.contentment.actor;

import org.geepawhill.contentment.fragments.Entrance;

import javafx.scene.Group;

public abstract class GenericActor implements Actor
{
	protected final ScriptWorld world;
	protected final Entrance entrance;

	public GenericActor(ScriptWorld world)
	{
		this.world = world;
		this.entrance = new Entrance(new Group());
	}
	
	public Entrance entrance()
	{
		return entrance;
	}	
	
	public Group group()
	{
		return entrance().group();
	}
}
