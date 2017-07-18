package org.geepawhill.contentment.actors;

import org.geepawhill.contentment.actor.Actor;
import org.geepawhill.contentment.atom.BezierAtom;
import org.geepawhill.contentment.atom.LettersAtom;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.geometry.Bezier;
import org.geepawhill.contentment.geometry.Jiggler;
import org.geepawhill.contentment.geometry.Point;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.position.Centered;
import org.geepawhill.contentment.step.AtomStep;
import org.geepawhill.contentment.step.Step;
import org.geepawhill.contentment.step.Timed;
import org.geepawhill.contentment.timing.Timing;
import org.geepawhill.contentment.utility.Names;

import javafx.scene.Group;

public class OvalText implements Actor
{
	final String nickname;
	final String source;

	private final Group group;

	private Jiggler controlJiggler;
	private Jiggler northJiggler;
	private LettersAtom letters;
	private BezierAtom east;
	private BezierAtom west;
	private Bezier eastHalfBezier;
	private Bezier westHalfBezier;

	public OvalText(String source, Point center, Format format)
	{
		this.nickname = Names.make(getClass());
		this.source = source;
		this.northJiggler = new Jiggler(.5d, 6d);
		this.controlJiggler = new Jiggler(.4d, 30d);

		letters = new LettersAtom(this, source, format, new Centered(center));
		east = new BezierAtom(this, this::eastHalfPoints, format);
		west = new BezierAtom(this, this::westHalfPoints, format);
		this.group = new Group();
	}

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
		timed.add(new AtomStep(Timing.weighted(.6d), letters));
		timed.add(new AtomStep(Timing.weighted(.2d), east));
		timed.add(new AtomStep(Timing.weighted(.2d), west));
		return timed;
	}

	private void setPointsIfNeeded()
	{
		if(eastHalfBezier==null)
		{
		PointPair points = new PointPair(letters.text()).grow(45,8);
		eastHalfBezier= new Bezier(points.north(), controlJiggler.jiggle(points.northeast()), controlJiggler.jiggle(points.southeast()),
				points.south());
		westHalfBezier = new Bezier(points.south(), controlJiggler.jiggle(points.southwest()), controlJiggler.jiggle(points.northwest()),
				northJiggler.jiggle(points.north()));
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

}
