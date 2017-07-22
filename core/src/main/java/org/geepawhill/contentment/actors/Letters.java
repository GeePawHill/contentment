package org.geepawhill.contentment.actors;

import org.geepawhill.contentment.actor.Actor;
import org.geepawhill.contentment.actor.GenericActor;
import org.geepawhill.contentment.actor.ScriptWorld;
import org.geepawhill.contentment.atom.BezierAtom;
import org.geepawhill.contentment.atom.LettersAtom;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.geometry.Bezier;
import org.geepawhill.contentment.geometry.Jiggler;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.position.Position;
import org.geepawhill.contentment.step.Step;
import org.geepawhill.contentment.step.Timed;
import org.geepawhill.contentment.timing.Timing;
import org.geepawhill.contentment.utility.Names;

import javafx.scene.Group;

public class Letters extends GenericActor implements Actor
{
	private final String nickname;
	private final Group group;
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
		this.nickname = Names.make(getClass());
		this.group = new Group();
		this.northJiggler = new Jiggler(.5d, 6d);
		this.controlJiggler = new Jiggler(.4d, 30d);

		this.letters = new LettersAtom(this, source, format, position);
		this.east = new BezierAtom(this, this::eastHalfPoints, format);
		this.west = new BezierAtom(this, this::westHalfPoints, format);
	}

	public Letters withOval()
	{
		hasOval = true;
		return this;
	}

	public Letters at(Position position)
	{
		letters.at(position);
		return this;
	}

	public Letters format(Format format)
	{
		letters.format(format);
		return this;
	}

	@Override
	public String nickname()
	{
		return nickname;
	}

	@Override
	public Group group()
	{
		return group;
	}

	@Override
	public Step draw(double ms)
	{
		Timed timed = new Timed(ms);
		timed.add(Timing.weighted(7), letters);
		if (hasOval)
		{
			timed.add(Timing.weighted(1.5), east);
			timed.add(Timing.weighted(1.5), west);
		}
		return timed;
	}

	@Override
	public String toString()
	{
		return nickname() + " [" + letters.toString() + "]";
	}

	private void setPointsIfNeeded()
	{
		if (eastHalfBezier == null)
		{
			PointPair points = new PointPair(letters.text()).grow(45, 8);
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

	// this action can only be done to actors of type MyAgent
	public Letters lettersOnly()
	{
		// do something only this kind of agent can do
		return this;
	}

}
