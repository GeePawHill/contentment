package org.geepawhill.contentment.actors;

import org.geepawhill.contentment.actor.*;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.fragments.Curve;
import org.geepawhill.contentment.geometry.*;

public class Stroke extends GenericActor
{
	private final Curves curves;

	public Stroke(ScriptWorld world, PointPair points)
	{
		super(world);
		this.curves = new Curves(world,jiggle(points));
	}

	public Curve jiggle(PointPair points)
	{
		double variance = points.distance() * .1;
		Bezier chosen = new Bezier(
				points.from,
				world.jiggle(points.along(world.nextDouble()), 1d, variance),
				world.jiggle(points.along(world.nextDouble()), 1d, variance),
				points.to);
		return new Curve(groupSource(), () -> chosen, Format.DEFAULT);
	}

	public Stroke format(Format format)
	{
		curves.format(format);
		return this;
	}

	@Override
	public Stroke draw(double ms)
	{
		curves.draw(ms);
		return this;
	}

	public Stroke assume()
	{
		format(world.assumptions().format());
		return this;
	}
}
