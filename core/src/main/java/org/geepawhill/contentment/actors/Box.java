package org.geepawhill.contentment.actors;

import java.util.Random;

import org.geepawhill.contentment.actor.GenericActor;
import org.geepawhill.contentment.actor.ScriptWorld;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.fragments.Curve;
import org.geepawhill.contentment.geometry.Bezier;
import org.geepawhill.contentment.geometry.PointPair;

public class Box extends GenericActor
{
	private final Curves curves;
	private final Random random;

	public Box(ScriptWorld world, PointPair points)
	{
		super(world);
		this.random = new Random();
		this.curves = new Curves(world, jiggle(points.northLine()), jiggle(points.eastLine()), jiggle(points.southLine()),
				jiggle(points.westLine()));
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

	public Box format(Format format)
	{
		curves.format(format);
		return this;
	}

	@Override
	public Box draw(double ms)
	{
		curves.draw(ms);
		return this;
	}

	public Box assume()
	{
		format(world.assumptions().format());
		return this;
	}
}
