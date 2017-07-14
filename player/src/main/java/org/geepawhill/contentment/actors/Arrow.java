package org.geepawhill.contentment.actors;

import java.util.ArrayList;
import java.util.Random;

import org.geepawhill.contentment.actor.Actor;
import org.geepawhill.contentment.atom.BezierAtom;
import org.geepawhill.contentment.connector.arrow.ArrowComputer;
import org.geepawhill.contentment.connector.arrow.ArrowPoints;
import org.geepawhill.contentment.connector.arrow.NodeArrowComputer;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.geometry.Bezier;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.step.AtomStep;
import org.geepawhill.contentment.step.Step;
import org.geepawhill.contentment.step.Timed;
import org.geepawhill.contentment.timing.Timing;
import org.geepawhill.contentment.utility.Names;

import javafx.scene.Group;

public class Arrow implements Actor
{
	final String nickname;

	private final Group group;

	private BezierAtom mainStep;
	private BezierAtom fromTopStep;
	private BezierAtom fromBottomStep;
	private BezierAtom toTopStep;
	private BezierAtom toBottomStep;

	private boolean pointAtFrom;
	private boolean pointAtTo;

	private ArrowComputer computer;
	private ArrowPoints points;

	private ArrayList<BezierAtom> steps;

	private Random random;

	public Arrow(Actor from, boolean pointAtFrom, Actor to, boolean pointAtTo, Format format)
	{
		this.random = new Random();
		this.nickname = Names.make(getClass());
		this.pointAtFrom = pointAtFrom;
		this.pointAtTo = pointAtTo;
		this.computer = new NodeArrowComputer(from.group(), to.group());
		this.group = new Group();
		steps = new ArrayList<>();
		mainStep = new BezierAtom(this,this::getMainBezier, format);
		if (pointAtFrom)
		{
			fromTopStep = new BezierAtom(this,this::getFromTop, format);
			steps.add(fromTopStep);
			fromBottomStep = new BezierAtom(this,this::getFromBottom, format);
			steps.add(fromBottomStep);
		}
		if (pointAtTo)
		{
			toTopStep = new BezierAtom(this,this::getToTop, format);
			steps.add(toTopStep);
			toBottomStep = new BezierAtom(this,this::getToBottom, format);
			steps.add(toBottomStep);
		}
	}

	public String nickname()
	{
		return nickname;
	}

	private Bezier getMainBezier()
	{
		points = computer.compute();
		return chooseBezier(points.main);
	}
	
	private Bezier getFromTop()
	{
		return chooseBezier(points.fromTop);
	}
	
	private Bezier getFromBottom()
	{
		return chooseBezier(points.fromBottom);
	}
	
	private Bezier getToTop()
	{
		return chooseBezier(points.toTop);
	}
	
	private Bezier getToBottom()
	{
		return chooseBezier(points.toBottom);
	}

	
	public Bezier chooseBezier(PointPair points)
	{
		return new Bezier(
				points.from, points.along(random.nextDouble()).jiggle(random, 1d, 10),
				points.along(random.nextDouble()).jiggle(random, 1d, 10), points.to
		);
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
		sequence.add(new AtomStep(Timing.weighted(.9d),mainStep));
		for (BezierAtom step : steps)
		{
			sequence.add(new AtomStep(Timing.weighted(.1d),step));
		}
		return sequence;
	}

}
