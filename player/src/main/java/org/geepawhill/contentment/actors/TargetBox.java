package org.geepawhill.contentment.actors;

import org.geepawhill.contentment.actor.Actor;
import org.geepawhill.contentment.core.Sequence;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.geometry.Bezier;
import org.geepawhill.contentment.geometry.Point;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.step.AddNode;
import org.geepawhill.contentment.step.BezierStep;
import org.geepawhill.contentment.step.SetBounds;
import org.geepawhill.contentment.step.LettersStep;
import org.geepawhill.contentment.timing.Timing;
import org.geepawhill.contentment.utility.Names;

import javafx.scene.Group;

public class TargetBox implements Actor
{
	final String nickname;
	final String source;

	private final Group group;

	private BezierStep northStep;
	private BezierStep southStep;
	private BezierStep westStep;
	private BezierStep eastStep;
	private LettersStep lettersStep;

	public TargetBox(String source, Point center, Format format)
	{
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
		PointPair grow = pair.change(4d, 4d, 300d, 300d);
		northStep.changeBezier(new Bezier(grow.northLine()));
		westStep.changeBezier(new Bezier(grow.westLine()));
		southStep.changeBezier(new Bezier(grow.southLine()));
		eastStep.changeBezier(new Bezier(grow.eastLine()));
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
		sequence.add(new AddNode(group, lettersStep));
		sequence.add(lettersStep);
		sequence.add(new SetBounds(lettersStep, this::boundsChanged));
		sequence.add(new AddNode(group, northStep));
		sequence.add(northStep);
		sequence.add(new AddNode(group, eastStep));
		sequence.add(eastStep);
		sequence.add(new AddNode(group, southStep));
		sequence.add(southStep);
		sequence.add(new AddNode(group, westStep));
		sequence.add(westStep);
		return sequence.schedule(ms);
	}

}
