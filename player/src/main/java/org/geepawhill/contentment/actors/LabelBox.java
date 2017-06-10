package org.geepawhill.contentment.actors;

import java.util.Random;

import org.geepawhill.contentment.actor.Actor;
import org.geepawhill.contentment.core.Sequence;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.geometry.Bezier;
import org.geepawhill.contentment.geometry.Point;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.step.AddNodeStep;
import org.geepawhill.contentment.step.BezierStep;
import org.geepawhill.contentment.step.BoundsStep;
import org.geepawhill.contentment.step.LettersStep;
import org.geepawhill.contentment.timing.Timing;
import org.geepawhill.contentment.utility.Names;

import javafx.scene.Group;

public class LabelBox implements Actor
{
	final String nickname;
	final String source;

	private final Group group;

	private Point center;

	private BezierStep northStep;
	private BezierStep southStep;
	private BezierStep westStep;
	private BezierStep eastStep;
	private LettersStep lettersStep;
	private Random random;
	


	public LabelBox(String source, Point center, Format format)
	{
		this.random = new Random();
		this.nickname = Names.make(getClass());
		this.center = center;
		this.source = source;
		this.group = new Group();
		lettersStep = new LettersStep(Timing.weighted(.6d), source, center, format);
		northStep = new BezierStep(Timing.weighted(.1d), new PointPair(0d, 0d, 0d, 0d), format);
		westStep = new BezierStep(Timing.weighted(.1d), new PointPair(0d, 0d, 0d, 0d), format);
		southStep = new BezierStep(Timing.weighted(.1d), new PointPair(0d, 0d, 0d, 0d), format);
		eastStep = new BezierStep(Timing.weighted(.1d), new PointPair(0d, 0d, 0d, 0d), format);
	}

	public String nickname()
	{
		return nickname;
	}

	private void boundsChanged(PointPair pair)
	{
		PointPair grow = pair.grow(4d);
		northStep.setBezier(chooseControlPoints(grow.northLine()));
		westStep.setBezier(chooseControlPoints(grow.westLine()));
		southStep.setBezier(chooseControlPoints(grow.southLine()));
		eastStep.setBezier(chooseControlPoints(grow.eastLine()));
	}
	
	public Bezier chooseControlPoints(PointPair points)
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
		sequence.add(new AddNodeStep(group,lettersStep));
		sequence.add(lettersStep);
		sequence.add(new BoundsStep(lettersStep, this::boundsChanged));
		sequence.add(new AddNodeStep(group,northStep));
		sequence.add(northStep);
		sequence.add(new AddNodeStep(group,eastStep));
		sequence.add(eastStep);
		sequence.add(new AddNodeStep(group,southStep));
		sequence.add(southStep);
		sequence.add(new AddNodeStep(group,westStep));
		sequence.add(westStep);
		sequence.schedule(ms);
		return sequence;
	}

}
