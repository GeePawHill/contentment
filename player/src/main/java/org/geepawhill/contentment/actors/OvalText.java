package org.geepawhill.contentment.actors;

import org.geepawhill.contentment.actor.Actor;
import org.geepawhill.contentment.atom.LettersAtom;
import org.geepawhill.contentment.fast.AddNode;
import org.geepawhill.contentment.fast.SetBounds;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.geometry.Bezier;
import org.geepawhill.contentment.geometry.Jiggler;
import org.geepawhill.contentment.geometry.Point;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.position.Centered;
import org.geepawhill.contentment.step.AtomStep;
import org.geepawhill.contentment.step.BezierStep;
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

	private Step lettersStep;
	private BezierStep eastStep;
	private BezierStep westStep;

	private Jiggler controlJiggler;
	private Jiggler northJiggler;
	private LettersAtom atom;

	public OvalText(String source, Point center, Format format)
	{
		this.nickname = Names.make(getClass());
		this.source = source;
		this.northJiggler = new Jiggler(.5d, 6d);
		this.controlJiggler = new Jiggler(.4d, 30d);

		atom = new LettersAtom(this,source, format, new Centered(center));
		lettersStep = new AtomStep(Timing.weighted(.6d), atom);
		eastStep = new BezierStep(Timing.weighted(.2d), format);
		westStep = new BezierStep(Timing.weighted(.2d), format);
		this.group = new Group();
	}

	public String nickname()
	{
		return nickname;
	}

	private void boundsChanged(PointPair pair)
	{
		PointPair grow = pair.grow(45d, 8d);
		eastStep.changeBezier(eastHalfPoints(grow));
		westStep.changeBezier(westHalfPoints(grow));
	}

	@Override
	public Group group()
	{
		return group;
	}

	@Override
	public Step draw(double ms)
	{
		Timed sequence = new Timed(ms);
		sequence.add(lettersStep);
		sequence.add(new SetBounds(atom.text(), this::boundsChanged));
		sequence.add(new AddNode(group, eastStep));
		sequence.add(eastStep);
		sequence.add(new AddNode(group, westStep));
		sequence.add(westStep);
		return sequence;
	}

	private Bezier eastHalfPoints(PointPair points)
	{
		return new Bezier(points.north(), controlJiggler.jiggle(points.northeast()), controlJiggler.jiggle(points.southeast()),
				points.south());
	}

	public Bezier westHalfPoints(PointPair points)
	{
		return new Bezier(points.south(), controlJiggler.jiggle(points.southwest()), controlJiggler.jiggle(points.northwest()),
				northJiggler.jiggle(points.north()));
	}

}
