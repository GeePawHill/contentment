package org.geepawhill.contentment.actors;

import java.util.Random;

import org.geepawhill.contentment.actor.Actor;
import org.geepawhill.contentment.core.Sequence;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.geometry.Bezier;
import org.geepawhill.contentment.geometry.Point;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.step.AddNode;
import org.geepawhill.contentment.step.BezierStep;
import org.geepawhill.contentment.step.SetBounds;
import org.geepawhill.contentment.step.Step;
import org.geepawhill.contentment.step.Timed;
import org.geepawhill.contentment.step.LettersStep;
import org.geepawhill.contentment.timing.Timing;
import org.geepawhill.contentment.utility.Names;

import javafx.scene.Group;

public class LabelBox implements Actor
{
	final String nickname;
	final String source;

	private final Group group;

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
		this.source = source;
		this.group = new Group();
		lettersStep = new LettersStep(Timing.weighted(.6d), source, center, format);
		northStep = new BezierStep(Timing.weighted(.1d), format);
		westStep = new BezierStep(Timing.weighted(.1d), format);
		southStep = new BezierStep(Timing.weighted(.1d), format);
		eastStep = new BezierStep(Timing.weighted(.1d), format);
	}

	public String nickname()
	{
		return nickname;
	}

	private void boundsChanged(PointPair pair)
	{
		PointPair grow = pair.grow(4d);
		northStep.changeBezier(chooseControlPoints(grow.northLine()));
		westStep.changeBezier(chooseControlPoints(grow.westLine()));
		southStep.changeBezier(chooseControlPoints(grow.southLine()));
		eastStep.changeBezier(chooseControlPoints(grow.eastLine()));
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
	public Step draw(double ms)
	{
		Timed sequence = new Timed(ms);
		sequence.add(new AddNode(group,lettersStep));
		sequence.add(lettersStep);
		sequence.add(new SetBounds(lettersStep, this::boundsChanged));
		sequence.add(new AddNode(group,northStep));
		sequence.add(northStep);
		sequence.add(new AddNode(group,eastStep));
		sequence.add(eastStep);
		sequence.add(new AddNode(group,southStep));
		sequence.add(southStep);
		sequence.add(new AddNode(group,westStep));
		sequence.add(westStep);
		return sequence;
	}

}
