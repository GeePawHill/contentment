package org.geepawhill.contentment.actors;

import java.util.ArrayList;
import java.util.Random;

import org.geepawhill.contentment.actor.Actor;
import org.geepawhill.contentment.actor.GenericActor;
import org.geepawhill.contentment.actor.ScriptWorld;
import org.geepawhill.contentment.atom.BezierAtom;
import org.geepawhill.contentment.atom.GroupSource;
import org.geepawhill.contentment.connector.arrow.ArrowComputer;
import org.geepawhill.contentment.connector.arrow.ArrowPoints;
import org.geepawhill.contentment.connector.arrow.NodeArrowComputer;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.geometry.Bezier;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.step.Step;
import org.geepawhill.contentment.step.Timed;
import org.geepawhill.contentment.timing.Timing;
import org.geepawhill.contentment.utility.Names;

import javafx.scene.Group;

public class Arrow extends GenericActor
{
	final String nickname;

	private BezierAtom mainStep;
	private BezierAtom fromTopStep;
	private BezierAtom fromBottomStep;
	private BezierAtom toTopStep;
	private BezierAtom toBottomStep;

	private ArrowComputer computer;
	private ArrowPoints points;

	private ArrayList<BezierAtom> steps;

	private Random random;

	private Bezier chosenMain;
	private Bezier chosenFromTop;
	private Bezier chosenFromBottom;
	private Bezier chosenToTop;
	private Bezier chosenToBottom;

	public Arrow(ScriptWorld world, Actor from, boolean pointAtFrom, Actor to, boolean pointAtTo, Format format)
	{
		this(world,from.groupSource(),pointAtFrom,to.groupSource(),pointAtTo,format);
	}
	
	public Arrow(ScriptWorld world, GroupSource from, boolean pointAtFrom, GroupSource to, boolean pointAtTo, Format format)
	{
		super(world);
		this.random = new Random();
		this.nickname = Names.make(getClass());
		this.computer = new NodeArrowComputer(from, to);
		steps = new ArrayList<>();
		chosenMain = null;
		mainStep = new BezierAtom(groupSource(), this::getMainBezier, format);
		if (pointAtFrom)
		{
			fromTopStep = new BezierAtom(groupSource(), this::getFromTop, format);
			steps.add(fromTopStep);
			fromBottomStep = new BezierAtom(groupSource(), this::getFromBottom, format);
			steps.add(fromBottomStep);
		}
		if (pointAtTo)
		{
			toTopStep = new BezierAtom(groupSource(), this::getToTop, format);
			steps.add(toTopStep);
			toBottomStep = new BezierAtom(groupSource(), this::getToBottom, format);
			steps.add(toBottomStep);
		}
	}

	public String nickname()
	{
		return nickname;
	}

	private Bezier getMainBezier()
	{
		if (chosenMain == null)
		{
			points = computer.compute();
			chosenMain = chooseBezier(points.main);
			chosenFromTop = chooseBezier(points.fromTop);
			chosenToTop = chooseBezier(points.toTop);
			chosenFromBottom = chooseBezier(points.fromBottom);
			chosenToBottom = chooseBezier(points.toBottom);
		}
		return chosenMain;
	}

	private Bezier getFromTop()
	{
		return chosenFromTop;
	}

	private Bezier getFromBottom()
	{
		return chosenFromBottom;
	}

	private Bezier getToTop()
	{
		return chosenToTop;
	}

	private Bezier getToBottom()
	{
		return chosenToBottom;
	}

	public Bezier chooseBezier(PointPair points)
	{
		Bezier chosen = new Bezier(points.from, points.along(random.nextDouble()).jiggle(random, 1d, 10),
				points.along(random.nextDouble()).jiggle(random, 1d, 10), points.to);
		return chosen;
	}

	@Override
	public Group group()
	{
		return entrance.get();
	}

	@Override
	public Step draw(double ms)
	{
		Timed sequence = new Timed(ms);
		sequence.add(Timing.weighted(.9d), mainStep);
		for (BezierAtom step : steps)
		{
			sequence.add(Timing.weighted(.1d), step);
		}
		return sequence;
	}

}
