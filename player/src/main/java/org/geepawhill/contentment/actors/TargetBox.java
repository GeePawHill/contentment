package org.geepawhill.contentment.actors;

import org.geepawhill.contentment.actor.Actor;
import org.geepawhill.contentment.core.Sequence;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.geometry.Point;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.step.AddNodeStep;
import org.geepawhill.contentment.step.BoundsStep;
import org.geepawhill.contentment.step.LettersStep;
import org.geepawhill.contentment.step.Step;
import org.geepawhill.contentment.step.StrokeStep;
import org.geepawhill.contentment.step.TransitionStep;
import org.geepawhill.contentment.timing.Timing;
import org.geepawhill.contentment.utility.Names;

import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.util.Duration;

public class TargetBox implements Actor
{
	final String nickname;
	final String source;
	
	private final Group group;

	private Point center;
	
	private StrokeStep northStep;
	private StrokeStep southStep;
	private StrokeStep westStep;
	private StrokeStep eastStep;
	private LettersStep lettersStep;

	public TargetBox(String source, Point center, Format format)
	{
		this.nickname = Names.make(getClass());
		this.center = center;
		this.source = source;
		this.group = new Group();
		lettersStep = new LettersStep(Timing.weighted(.6d), source, center, format);
		northStep = new StrokeStep(Timing.weighted(.1d),new PointPair(0d,0d,0d,0d),format);
		westStep = new StrokeStep(Timing.weighted(.1d), new PointPair(0d,0d,0d,0d),format);
		southStep = new StrokeStep(Timing.weighted(.1d),new PointPair(0d,0d,0d,0d),format);
		eastStep = new StrokeStep(Timing.weighted(.1d), new PointPair(0d,0d,0d,0d),format);
	}
	
	public String nickname()
	{
		return nickname;
	}

	private void boundsChanged(PointPair pair)
	{
		PointPair grow = pair.change(4d,4d,300d,300d);
		northStep.setPoints(grow.northLine());
		westStep.setPoints(grow.westLine());
		southStep.setPoints(grow.southLine());
		eastStep.setPoints(grow.eastLine());
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
		sequence.add(new BoundsStep(lettersStep,this::boundsChanged));
		sequence.add(new AddNodeStep(group, northStep));
		sequence.add(northStep);
		sequence.add(new AddNodeStep(group, eastStep));
		sequence.add(eastStep);
		sequence.add(new AddNodeStep(group, southStep));
		sequence.add(southStep);
		sequence.add(new AddNodeStep(group, westStep));
		sequence.add(westStep);
		return sequence.schedule(ms);
	}

}
