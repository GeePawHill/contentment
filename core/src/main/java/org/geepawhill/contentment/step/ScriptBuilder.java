package org.geepawhill.contentment.step;

import org.geepawhill.contentment.actor.Actor;
import org.geepawhill.contentment.actor.Party;
import org.geepawhill.contentment.actor.ScriptWorld;
import org.geepawhill.contentment.actors.Arrow;
import org.geepawhill.contentment.actors.Letters;
import org.geepawhill.contentment.actors.Spot;
import org.geepawhill.contentment.actors.Stroke;
import org.geepawhill.contentment.atom.ClearAtom;
import org.geepawhill.contentment.atom.MarkAtom;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.timing.Timing;

public abstract class ScriptBuilder<SUBCLASS>
{
	
	protected ScriptWorld world;

	public ScriptBuilder()
	{
		world = new ScriptWorld();
	}
	
	public abstract SUBCLASS downcast();
	
	public SUBCLASS cue(long beat)
	{
		addToWorking(new AtomStep(Timing.ms(30000),new MarkAtom(beat*1000)));
		return downcast();
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
	
	public Letters letters(String source)
	{
		return new Letters(world,source);
	}
	
	public Party party(String name)
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
	
	public SUBCLASS wipe()
	{
		world.add(new AtomStep(Timing.instant(),new ClearAtom()));
		return downcast();
	}

	public Actor spot(double x, double y)
	{
		return new Spot(world,x,y);
	}

}
