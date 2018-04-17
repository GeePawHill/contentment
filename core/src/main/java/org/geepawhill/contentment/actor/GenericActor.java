package org.geepawhill.contentment.actor;

import org.geepawhill.contentment.fragments.Entrance;

import javafx.scene.Group;

public abstract class GenericActor implements Actor
{
	protected final ScriptWorld world;
	protected final Entrance entrance;
	protected final Group group;

	public GenericActor(ScriptWorld world)
	{
		this.world = world;
		this.group = new Group();
		this.entrance = new Entrance(this.group);
	}
	
	public Entrance entrance()
	{
		return entrance;
	}	
	
	public Group group()
	{
		return group;
	}
}
