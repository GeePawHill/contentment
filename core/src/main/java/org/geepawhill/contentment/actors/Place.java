package org.geepawhill.contentment.actors;

import org.geepawhill.contentment.actor.GenericActor;
import org.geepawhill.contentment.actor.ScriptWorld;
import org.geepawhill.contentment.fragments.Spot;
import org.geepawhill.contentment.geometry.Point;
import org.geepawhill.contentment.step.AtomStep;
import org.geepawhill.contentment.timing.Timing;
import org.geepawhill.contentment.utility.Names;

public class Place extends GenericActor
{

	final String nickname;
	
	final Spot atom;
	
	public Place(ScriptWorld world,double x, double y)
	{
		this(world,Names.make(Place.class.getSimpleName()),x, y);
	}
	
	public Place(ScriptWorld world,String name, double x, double y)
	{
		super(world);
		this.nickname = name;
		this.atom = new Spot(groupSource(),new Point(x,y));
	}
	
	public String nickname()
	{
		return nickname;
	}
	
	@Override
	public Place draw(double ms)
	{
		world.add(new AtomStep(Timing.ms(ms),atom));
		return this;
	}
}
