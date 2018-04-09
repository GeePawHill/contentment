package org.geepawhill.contentment.step;

import org.geepawhill.contentment.actor.*;
import org.geepawhill.contentment.actors.*;
import org.geepawhill.contentment.core.Gesture;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.fragments.*;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.player.*;
import org.geepawhill.contentment.rhythm.*;
import org.geepawhill.contentment.timing.Timing;

public abstract class ScriptBuilder<SUBCLASS>
{
	
	protected ScriptWorld world;
	protected Script script;
	protected long lastScene;
	protected long lastStall;

	public ScriptBuilder()
	{
		this(new SimpleRhythm());
	}
	
	public ScriptBuilder(Rhythm rhythm)
	{
		this.script = new Script(rhythm);
		this.lastScene = -1L;
		this.world = new ScriptWorld();
	}
	
	public abstract SUBCLASS downcast();
	
	public void scene(long beat)
	{
		if(lastScene!=-1)
		{
			script.add(new Keyframe(lastScene,endBuild()));
		}
		lastScene = beat;
		lastStall=beat;
		buildPhrase();
		addToWorking(new AtomStep(Timing.ms(30000),new Sync(beat*1000)));
	}

	public void end()
	{
		if(lastScene==-1) throw new RuntimeException("end() called with no scene.");
		script.add(new Keyframe(lastScene,endBuild()));
		lastScene=-1;
	}
	
	public void sync(long beat)
	{
		if(lastScene==-1) throw new RuntimeException("end() called with no scene.");
		lastStall+=beat;
		addToWorking(new AtomStep(Timing.ms(30000),new Sync(lastStall*1000)));
	}
	
	protected void addToWorking(Gesture step)
	{
		world.add(step);
	}
	
	public Phrase makePhrase()
	{
		return new Phrase();
	}

	public void buildPhrase()
	{
		world.push(makePhrase());
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
	
	public Appearance<Letters> letters(String source)
	{
		return new Appearance<>(world,new Letters(world,source));
	}
	
	public Appearance<Marks> stroke(int fromX, int fromY, int toX, int toY) {
		return new Appearance<>(world,Marks.makeLine(world,new PointPair(fromX,fromY,toX,toY)));
		}
	
	public Appearance<Cross> cross(String name, double xsize, double ysize, double xoffset, double yoffset)
	{
		return new Appearance<>(world,new Cross(world,actor(name).entrance(),xsize,ysize,xoffset,yoffset));
	}

	public Appearance<Marks> stroke(PointPair points)
	{
		return new Appearance<>(world,Marks.makeLine(world,points));
	}
	
	public Appearance<Marks> box(PointPair area)
	{
		return new Appearance<>(world,Marks.makeBox(world,area));
	}

	public Appearance<Connector> connector()
	{
		return new Appearance<>(world,new Connector(world));
	}
	
	public SUBCLASS wipe()
	{
		world.add(new AtomStep(Timing.instant(),new Wipe()));
		return downcast();
	}
	
	public SUBCLASS assume(Format format)
	{
		world.assumptions().assume(format);
		return downcast();
	}
	
}
