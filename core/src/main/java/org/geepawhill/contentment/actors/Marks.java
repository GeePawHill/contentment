package org.geepawhill.contentment.actors;

import org.geepawhill.contentment.actor.*;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.geometry.*;

public class Marks extends GenericActor
{

	protected final Curves curves;

	public Marks(ScriptWorld world,Bezier...beziers)
	{
		super(world);
		this.curves = new Curves(world, groupSource(), beziers);
	}

	public Marks format(Format format)
	{
		curves.format(format);
		return this;
	}

	@Override
	public Marks draw(double ms)
	{
		curves.draw(ms);
		return this;
	}

	public Marks assume()
	{
		format(world.assumptions().format());
		return this;
	}

	public static Marks makeBox(ScriptWorld world, PointPair points)
	{
		return new Marks(world, jiggle(world, points.northLine()), jiggle(world, points.eastLine()), jiggle(world, points.southLine()),
				jiggle(world, points.westLine()));
	}
	
	public static Marks makeLine(ScriptWorld world, PointPair points)
	{
		return new Marks(world, jiggle(world, points));
	}

	private static Bezier jiggle(ScriptWorld world, PointPair points)
	{
		double variance = points.distance() * .1;
		Bezier chosen = new Bezier(points.from, world.jiggle(points.along(world.nextDouble()), 1d, variance),
				world.jiggle(points.along(world.nextDouble()), 1d, variance), points.to);
		return chosen;
	}


}