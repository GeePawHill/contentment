package org.geepawhill.contentment.actor;

import org.geepawhill.contentment.actors.Letters;
import org.geepawhill.contentment.actors.Slide;
import org.geepawhill.contentment.actors.Stroke;
import org.geepawhill.contentment.geometry.PointPair;

public class CueBuilder
{
	
	private ScriptWorld world;

	public CueBuilder(ScriptWorld world,long beat)
	{
		this.world = world;
	}
	
	public Actor actor(Actor actor)
	{
		return actor;
	}
	
	public Actor actor(String actor)
	{
		return actor(world.actor(actor));
	}
	
	public Slide slide()
	{
		return world.slide();
	}

	public Letters letters(String source)
	{
		return new Letters(world,source);
	}
	
	public Actors party(String name)
	{
		return world.party(name);
	}

	public Stroke stroke(PointPair westLine)
	{
		return new Stroke(world,westLine);
	}

}
