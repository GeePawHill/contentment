package org.geepawhill.contentment.actors;

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
import org.geepawhill.contentment.style.Dash;
import org.geepawhill.contentment.style.Frames;
import org.geepawhill.contentment.style.TypeFace;
import org.geepawhill.contentment.timing.Timing;
import org.geepawhill.contentment.utility.Names;

import javafx.scene.Group;
import javafx.scene.paint.Color;

public class Placeholder implements Actor
{
	final String nickname;
	final String source;

	private final Group group;

	private PointPair bounds;

	private BezierStep northStep;
	private BezierStep southStep;
	private BezierStep westStep;
	private BezierStep eastStep;
	private Format format;
	private LettersStep lettersStep;

	public Placeholder(String source, Point center, double width, double height)
	{
		this(source, PointPair.centerAt(center, width, height));
	}

	public Placeholder(String source, PointPair bounds)
	{
		this.format = new Format(TypeFace.color(source, Color.YELLOW, 1d), TypeFace.smallFixed(),
				Frames.frame(Color.YELLOW, 1d, 1d, Dash.dash(4d, 4d)));
		this.nickname = Names.make(getClass());
		this.bounds = bounds;
		this.source = source;
		lettersStep = new LettersStep(Timing.weighted(.6d), source, bounds.grow(-32d).north(), format);
		northStep = new BezierStep(Timing.weighted(.1d), format);
		westStep = new BezierStep(Timing.weighted(.1d), format);
		southStep = new BezierStep(Timing.weighted(.1d), format);
		eastStep = new BezierStep(Timing.weighted(.1d), format);
		this.group = new Group();
	}

	public String nickname()
	{
		return nickname;
	}

	private void boundsChanged(PointPair pair)
	{
		northStep.changeBezier(new Bezier(bounds.northLine()));
		westStep.changeBezier(new Bezier(bounds.westLine()));
		southStep.changeBezier(new Bezier(bounds.southLine()));
		eastStep.changeBezier(new Bezier(bounds.eastLine()));
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
		return sequence.schedule(ms);
	}

}
