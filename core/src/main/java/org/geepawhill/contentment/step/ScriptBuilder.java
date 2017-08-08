package org.geepawhill.contentment.step;

import org.geepawhill.contentment.actor.Actor;
import org.geepawhill.contentment.actor.Actors;
import org.geepawhill.contentment.actor.Commandable;
import org.geepawhill.contentment.actor.ScriptWorld;
import org.geepawhill.contentment.actors.Arrow;
import org.geepawhill.contentment.actors.Letters;
import org.geepawhill.contentment.actors.Slide;
import org.geepawhill.contentment.actors.Spot;
import org.geepawhill.contentment.actors.Stroke;
import org.geepawhill.contentment.atom.ClearAtom;
import org.geepawhill.contentment.atom.MarkAtom;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.timing.Timing;

public class ScriptBuilder
{
	
	protected ScriptWorld world;

	public ScriptBuilder()
	{
		world = new ScriptWorld();
	}
	
	public ScriptBuilder cue(long beat)
	{
		addToWorking(new AtomStep(Timing.ms(30000),new MarkAtom(beat*1000)));
		return this;
	}
	
	public ScriptBuilder and()
	{
		return this;
	}
	
	private void addToWorking(Step step)
	{
		world.add(step);
	}
	
	public Addable buildPhrase()
	{
		return world.buildPhrase();
	}
	
	public Addable endBuild()
	{
		return world.endPhrase();
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
	
	public ScriptBuilder wipe()
	{
		world.add(new AtomStep(Timing.instant(),new ClearAtom()));
		return this;
	}

	public Commandable spot(double x, double y)
	{
		return new Spot(world,x,y);
	}

}
