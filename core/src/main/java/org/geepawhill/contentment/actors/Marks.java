package org.geepawhill.contentment.actors;

import org.geepawhill.contentment.actor.*;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.fragments.Mark;
import org.geepawhill.contentment.geometry.*;
import org.geepawhill.contentment.position.Position;
import org.geepawhill.contentment.step.Single;
import org.geepawhill.contentment.timing.Timing;

import javafx.scene.Group;

public class Marks extends GenericActor
{
	private final Mark[] marks;

	public Marks(ScriptWorld world, Group destination, Bezier... beziers)
	{
		super(world);
		this.marks = new Mark[beziers.length];
		int next = 0;
		for (Bezier bezier : beziers)
		{
			marks[next++] = new Mark(entrance(), () -> bezier);
		}
	}

	@Override
	public void format(Format format)
	{
		for (Mark curve : marks)
		{
			curve.format(format);
		}
	}

	@Override
	public Marks draw(double ms)
	{
		for (Mark curve : marks)
		{
			world.add(new Single(Timing.ms(ms / marks.length), curve));
		}
		return this;
	}

	public static Marks makeBox(ScriptWorld world, PointPair points)
	{
		return new Marks(world, new Group(), jiggle(world, points.northLine()),
				jiggle(world, points.eastLine()), jiggle(world, points.southLine()), jiggle(world, points.westLine()));
	}

	public static Marks makeLine(ScriptWorld world, PointPair points)
	{
		return new Marks(world, new Group(), jiggle(world, points));
	}

	private static Bezier jiggle(ScriptWorld world, PointPair points)
	{
		double variance = points.distance() * .05;
		Bezier chosen = new Bezier(points.from, world.jiggle(points.along(world.nextDouble()), 1d, variance),
				world.jiggle(points.along(world.nextDouble()), 1d, variance), points.to);
		return chosen;
	}

	@Override
	public void at(Position position)
	{
	}

}