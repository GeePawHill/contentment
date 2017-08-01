package org.geepawhill.contentment.actors;

import org.geepawhill.contentment.actor.GenericActor;
import org.geepawhill.contentment.actor.ScriptWorld;
import org.geepawhill.contentment.atom.SpotAtom;
import org.geepawhill.contentment.geometry.Point;
import org.geepawhill.contentment.step.AtomStep;
import org.geepawhill.contentment.step.Step;
import org.geepawhill.contentment.timing.Timing;
import org.geepawhill.contentment.utility.Names;

import javafx.scene.Group;

public class Spot extends GenericActor
{

	final String nickname;
	
	final SpotAtom atom;
	
	public Spot(ScriptWorld world,double x, double y)
	{
		this(world,Names.make(Spot.class.getSimpleName()),x, y);
	}
	
	public Spot(ScriptWorld world,String name, double x, double y)
	{
		super(world);
		this.nickname = name;
		this.atom = new SpotAtom(groupSource(),new Point(x,y));
	}
	
	public String nickname()
	{
		return nickname;
	}
	
	@Override
	public Group group()
	{
		return entrance.get();
	}
	
	@Override
	public Step draw(double ms)
	{
		return new AtomStep(Timing.ms(ms),atom);
	}
}
