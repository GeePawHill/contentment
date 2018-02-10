package org.geepawhill.contentment.actors;

import org.geepawhill.contentment.actor.Actor;
import org.geepawhill.contentment.actor.GenericActor;
import org.geepawhill.contentment.actor.ScriptWorld;
import org.geepawhill.contentment.atom.BezierAtom;
import org.geepawhill.contentment.atom.LettersAtom;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.geometry.Bezier;
import org.geepawhill.contentment.geometry.Jiggler;
import org.geepawhill.contentment.geometry.Point;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.position.Centered;
import org.geepawhill.contentment.position.Position;
import org.geepawhill.contentment.step.Timed;
import org.geepawhill.contentment.timing.Timing;

public class Letters extends GenericActor implements Actor
{
	private LettersAtom letters;
	private boolean hasOval;

	private Jiggler controlJiggler;
	private Jiggler northJiggler;
	private BezierAtom east;
	private BezierAtom west;
	private Bezier eastHalfBezier;
	private Bezier westHalfBezier;

	public Letters(ScriptWorld world, String source)
	{
		this(world, source, Position.DEFAULT, Format.DEFAULT);
	}

	public Letters(ScriptWorld world, String source, Position position, Format format)
	{
		super(world);
		this.northJiggler = new Jiggler(.5d, 6d);
		this.controlJiggler = new Jiggler(.4d, 30d);

		this.letters = new LettersAtom(groupSource(), source, format, position);
		this.east = new BezierAtom(groupSource(), this::eastHalfPoints, format);
		this.west = new BezierAtom(groupSource(), this::westHalfPoints, format);
	}

	public Letters withOval()
	{
		hasOval = true;
		return this;
	}

	@Override
	public Letters draw(double ms)
	{
		ms = letters.source.length()*25d;
		Timed timed = new Timed(ms);
		timed.add(Timing.weighted(7), letters);
		if (hasOval)
		{
			timed.add(Timing.weighted(1.5), east);
			timed.add(Timing.weighted(1.5), west);
		}
		world.add(timed);
		return this;
	}

	@Override
	public String toString()
	{
		return "Letters [" + letters.toString() + "]";
	}

	private void setPointsIfNeeded()
	{
		if (eastHalfBezier == null)
		{
			PointPair raw = new PointPair(letters.text());
			PointPair points = raw.grow(raw.width()*0.25, raw.height()*0.15);
			eastHalfBezier = new Bezier(points.north(), controlJiggler.jiggle(points.northeast()),
					controlJiggler.jiggle(points.southeast()), points.south());
			westHalfBezier = new Bezier(points.south(), controlJiggler.jiggle(points.southwest()),
					controlJiggler.jiggle(points.northwest()), northJiggler.jiggle(points.north()));
		}
	}

	private Bezier eastHalfPoints()
	{
		setPointsIfNeeded();
		return eastHalfBezier;
	}

	public Bezier westHalfPoints()
	{
		setPointsIfNeeded();
		return westHalfBezier;
	}

	public Letters at(Position position)
	{
		letters.at(position);
		return this;
	}

	public Letters format(Format format)
	{
		letters.format(format);
		east.format(format);
		west.format(format);
		return this;
	}

	public Letters assume()
	{
		return format(world.assumptions().format());
	}

	public Letters centered(double x, double y) {
		return at(new Centered(x,y));
	}
	
	public Letters centered(Point p) {
		return centered(p.x,p.y);
	}

}
