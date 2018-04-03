package org.geepawhill.contentment.actors;

import org.geepawhill.contentment.actor.*;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.fragments.Curve;
import org.geepawhill.contentment.geometry.*;
import org.geepawhill.contentment.step.AtomStep;
import org.geepawhill.contentment.timing.Timing;

public class Marks extends GenericActor
{

	private final Curve[] curves;

	public Marks(ScriptWorld world,Bezier...beziers)
	{
		super(world);
		this.curves = new Curve[beziers.length];
		int next = 0;
		for (Bezier bezier : beziers)
		{
			curves[next++] = new Curve(groupSource(), () -> bezier, Format.DEFAULT);
		}
	}
	
	public Marks format(Format format)
	{
		for (Curve curve : curves)
		{
			curve.format(format);
		}
		return this;
	}

	@Override
	public Marks draw(double ms)
	{
		for (Curve curve : curves)
		{
			world.add(new AtomStep(Timing.ms(ms / curves.length), curve));
		}
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