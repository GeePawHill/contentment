package org.geepawhill.contentment.actors;

import org.geepawhill.contentment.actor.Actor;
import org.geepawhill.contentment.core.Sequence;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.geometry.Point;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.step.AddNodeStep;
import org.geepawhill.contentment.step.BoundsStep;
import org.geepawhill.contentment.step.HandStep;
import org.geepawhill.contentment.step.LettersStep;
import org.geepawhill.contentment.step.Step;
import org.geepawhill.contentment.step.TransitionStep;
import org.geepawhill.contentment.timing.Timing;
import org.geepawhill.contentment.utility.Names;

import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.util.Duration;

public class LabelBox implements Actor
{
	final String nickname;
	final String source;

	private final Group group;

	private Point center;

	private HandStep northStep;
	private HandStep southStep;
	private HandStep westStep;
	private HandStep eastStep;
	private LettersStep lettersStep;

	public LabelBox(String source, Point center, Format format)
	{
		this.nickname = Names.make(getClass());
		this.center = center;
		this.source = source;
		this.group = new Group();
		lettersStep = new LettersStep(Timing.weighted(.6d), source, center, format);
		northStep = new HandStep(Timing.weighted(.1d), new PointPair(0d, 0d, 0d, 0d), format);
		westStep = new HandStep(Timing.weighted(.1d), new PointPair(0d, 0d, 0d, 0d), format);
		southStep = new HandStep(Timing.weighted(.1d), new PointPair(0d, 0d, 0d, 0d), format);
		eastStep = new HandStep(Timing.weighted(.1d), new PointPair(0d, 0d, 0d, 0d), format);
	}

	public String nickname()
	{
		return nickname;
	}

	private void boundsChanged(PointPair pair)
	{
		PointPair grow = pair.grow(4d);
		northStep.setPoints(grow.northLine());
		westStep.setPoints(grow.westLine());
		southStep.setPoints(grow.southLine());
		eastStep.setPoints(grow.eastLine());
	}

	public Step move(double newX, double newY)
	{
		TranslateTransition transition = new TranslateTransition();
		transition.setNode(group);
		transition.setToX(newX - center.x);
		transition.setToY(newY - center.y);
		transition.setDuration(Duration.millis(1000d));
		return new TransitionStep(transition);
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
		sequence.add(new BoundsStep(lettersStep.text, this::boundsChanged));
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
