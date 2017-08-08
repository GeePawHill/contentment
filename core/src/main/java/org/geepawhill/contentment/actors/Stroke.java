package org.geepawhill.contentment.actors;

import org.geepawhill.contentment.actor.GenericActor;
import org.geepawhill.contentment.actor.ScriptWorld;
import org.geepawhill.contentment.atom.BezierAtom;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.step.AtomStep;
import org.geepawhill.contentment.timing.Timing;
import org.geepawhill.contentment.utility.Names;

public class Stroke extends GenericActor
{
	private final String nickname;
	private final BezierAtom atom;
	
	public Stroke(ScriptWorld world, PointPair points)
	{
		this(world,points,Format.DEFAULT);
	}

	public Stroke(ScriptWorld world, PointPair points, Format format)
	{
		super(world);
		this.nickname = Names.make(getClass());
		this.atom = new BezierAtom(groupSource(),format,points);
	}
	
	public Stroke format(Format format)
	{
		atom.format(format);
		return this;
	}

	@Override
	public Stroke draw(double ms)
	{
		world.add(new AtomStep(Timing.ms(ms),atom));
		return this;
	}
}
