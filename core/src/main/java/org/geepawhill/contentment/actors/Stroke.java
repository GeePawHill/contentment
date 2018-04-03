package org.geepawhill.contentment.actors;

import java.util.Random;

import org.geepawhill.contentment.actor.*;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.fragments.Curve;
import org.geepawhill.contentment.geometry.*;

public class Stroke extends GenericActor
{
	private final Curves curves;
	private Random random;

	public Stroke(ScriptWorld world, PointPair points)
	{
		super(world);
		random = new Random();
		this.curves = new Curves(world,jiggle(points));
	}

	public Curve jiggle(PointPair points)
	{
		double variance = points.distance() * .1;
		Bezier chosen = new Bezier(
				points.from, 
				points.along(random.nextDouble()).jiggle(random, 1d, variance),
				points.along(random.nextDouble()).jiggle(random, 1d, variance), 
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
