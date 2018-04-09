package org.geepawhill.contentment.actor;

import org.geepawhill.contentment.fragments.Entrance;

public abstract class GenericActor implements Actor
{
	protected final ScriptWorld world;
	protected final Entrance entrance;

	public GenericActor(ScriptWorld world)
	{
		this.world = world;
		this.entrance = new Entrance();
	}
	
	public Entrance entrance()
	{
		return entrance;
	}

}
