package org.geepawhill.contentment.actor;

import org.geepawhill.contentment.actors.Arrow;
import org.geepawhill.contentment.actors.Letters;
import org.geepawhill.contentment.actors.Slide;
import org.geepawhill.contentment.actors.Spot;
import org.geepawhill.contentment.actors.Stroke;
import org.geepawhill.contentment.atom.ClearAtom;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.step.AtomStep;
import org.geepawhill.contentment.timing.Timing;

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

	public Arrow connector()
	{
		return new Arrow(world);
	}
	
	public CueBuilder wipe()
	{
		world.add(new AtomStep(Timing.instant(),new ClearAtom()));
		return this;
	}

	public Commandable spot(double x, double y)
	{
		return new Spot(world,x,y);
	}

}
