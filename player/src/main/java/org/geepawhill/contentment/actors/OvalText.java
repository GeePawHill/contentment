package org.geepawhill.contentment.actors;

import org.geepawhill.contentment.actor.Actor;
import org.geepawhill.contentment.core.Sequence;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.geometry.Point;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.step.BoundsStep;
import org.geepawhill.contentment.step.EntranceStep;
import org.geepawhill.contentment.step.HandOvalStep;
import org.geepawhill.contentment.step.LettersStep;
import org.geepawhill.contentment.timing.Scheduler;
import org.geepawhill.contentment.timing.Timing;
import org.geepawhill.contentment.utility.JfxUtility;
import org.geepawhill.contentment.utility.Names;

import javafx.scene.Group;

public class OvalText implements Actor
{
	final String nickname;
	final String source;

	private final Group group;

	private HandOvalStep ovalStep;
	private LettersStep lettersStep;

	public OvalText(String source, Point center, Format format)
	{
		this.nickname = Names.make(getClass());
		this.source = source;
		lettersStep = new LettersStep(Timing.weighted(.6d), source, center, format);
		ovalStep = new HandOvalStep(Timing.weighted(.4d), format);
		this.group = JfxUtility.makeGroup(this, lettersStep.text, ovalStep.shape());
	}
	
	
	public String nickname()
	{
		return nickname;
	}

	public void sketch(Sequence sequence, double ms)
	{
		new Scheduler().schedule(ms, lettersStep, ovalStep);
		sequence.add(new EntranceStep(this));
		sequence.add(lettersStep);
		sequence.add(new BoundsStep(lettersStep.text, this::boundsChanged));
		sequence.add(ovalStep);
	}

	private void boundsChanged(PointPair pair)
	{
		PointPair grow = pair.grow(45d,8d);
		ovalStep.setPoints(grow);
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
		sequence.add(lettersStep);
		sequence.add(new BoundsStep(lettersStep.text, this::boundsChanged));
		sequence.add(ovalStep);
		return sequence.schedule(ms);
	}
}
