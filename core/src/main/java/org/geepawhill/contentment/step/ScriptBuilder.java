package org.geepawhill.contentment.step;

import org.geepawhill.contentment.actor.Actor;
import org.geepawhill.contentment.actor.Party;
import org.geepawhill.contentment.actor.ScriptWorld;
import org.geepawhill.contentment.actors.Arrow;
import org.geepawhill.contentment.actors.Cross;
import org.geepawhill.contentment.actors.Letters;
import org.geepawhill.contentment.actors.Spot;
import org.geepawhill.contentment.actors.Stroke;
import org.geepawhill.contentment.atom.ClearAtom;
import org.geepawhill.contentment.atom.MarkAtom;
import org.geepawhill.contentment.core.Gesture;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.player.Script;
import org.geepawhill.contentment.rhythm.Rhythm;
import org.geepawhill.contentment.rhythm.SimpleRhythm;
import org.geepawhill.contentment.timing.Timing;

public abstract class ScriptBuilder<SUBCLASS>
{
	
	protected ScriptWorld world;
	private long lastCue;
	protected Script script;

	public ScriptBuilder()
	{
		this(new SimpleRhythm());
	}
	
	public ScriptBuilder(Rhythm rhythm)
	{
		this.script = new Script(rhythm);
		world = new ScriptWorld();
	}
	
	public abstract SUBCLASS downcast();
	
	public SUBCLASS cue(long beat)
	{
		addToWorking(new AtomStep(Timing.ms(30000),new MarkAtom(beat*1000)));
		lastCue = beat;
		return downcast();
	}
	
	public SUBCLASS offset(long offset)
	{
		return cue(lastCue+offset);
	}
	
	private void addToWorking(Gesture step)
	{
		world.add(step);
	}
	
	public void buildPhrase()
	{
		world.push(new Phrase());
	}
	
	public void buildChord()
	{
		world.push(new Chord());
	}
	
	public void endChord()
	{
		world.popAndAppend();
	}
	
	public Addable endBuild()
	{
		return world.pop();
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
		return new Letters(world,source).assume();
	}
	
	public Stroke stroke(int fromX, int fromY, int toX, int toY) {
		return new Stroke(world,new PointPair(fromX,fromY,toX,toY));
	}

	
	public Party party(String name)
	{
		return world.party(name);
	}
	
	public Cross cross(String name, double xsize, double ysize, double xoffset, double yoffset)
	{
		return new Cross(world,actor(name).groupSource(),xsize,ysize,xoffset,yoffset);
	}

	public Stroke stroke(PointPair westLine)
	{
		return new Stroke(world,westLine);
	}

	public Arrow connector()
	{
		return new Arrow(world).assume();
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
	
	public SUBCLASS assume(Format format)
	{
		world.assumptions().assume(format);
		return downcast();
	}
	
}
