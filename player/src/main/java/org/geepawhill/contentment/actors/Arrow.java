package org.geepawhill.contentment.actors;

import java.util.ArrayList;
import java.util.Random;

import org.geepawhill.contentment.actor.Actor;
import org.geepawhill.contentment.connector.arrow.ArrowComputer;
import org.geepawhill.contentment.connector.arrow.ArrowPoints;
import org.geepawhill.contentment.connector.arrow.NodeArrowComputer;
import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.Sequence;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.geometry.Bezier;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.step.AddNode;
import org.geepawhill.contentment.step.BezierStep;
import org.geepawhill.contentment.step.Compute;
import org.geepawhill.contentment.step.ShapeStep;
import org.geepawhill.contentment.timing.Timing;
import org.geepawhill.contentment.utility.Names;

import javafx.scene.Group;

public class Arrow implements Actor
{
	final String nickname;

	private final Group group;

	private BezierStep mainStep;
	private BezierStep fromTopStep;
	private BezierStep fromBottomStep;
	private BezierStep toTopStep;
	private BezierStep toBottomStep;

	private boolean pointAtFrom;
	private boolean pointAtTo;

	private ArrowComputer computer;
	private ArrowPoints points;

	private ArrayList<ShapeStep> steps;

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
		mainStep = new BezierStep(Timing.weighted(.9d), format);
		steps.add(mainStep);
		if (pointAtFrom)
		{
			fromTopStep = new BezierStep(Timing.weighted(.1d), format);
			steps.add(fromTopStep);
			fromBottomStep = new BezierStep(Timing.weighted(.1d), format);
			steps.add(fromBottomStep);
		}
		if (pointAtTo)
		{
			toTopStep = new BezierStep(Timing.weighted(.1d), format);
			steps.add(toTopStep);
			toBottomStep = new BezierStep(Timing.weighted(.1d), format);
			steps.add(toBottomStep);
		}
	}

	public String nickname()
	{
		return nickname;
	}

	private void computePoints(Context context, double fraction)
	{
		points = computer.compute();
		mainStep.changeBezier(chooseBezier(points.main));
		if (pointAtFrom)
		{
			fromTopStep.changeBezier(new Bezier(points.fromTop));
			fromBottomStep.changeBezier(new Bezier(points.fromBottom));
		}
		if (pointAtTo)
		{
			toTopStep.changeBezier(new Bezier(points.toTop));
			toBottomStep.changeBezier(new Bezier(points.toBottom));
		}
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
	public Sequence draw(double ms)
	{
		Sequence sequence = new Sequence();
		sequence.add(new Compute(this::computePoints));
		for (ShapeStep step : steps)
		{
			sequence.add(new AddNode(group, step));
			sequence.add(step);
		}
		sequence = sequence.schedule(ms);
		return sequence;
	}

}
