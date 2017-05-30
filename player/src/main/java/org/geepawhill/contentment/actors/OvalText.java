package org.geepawhill.contentment.actors;

import org.geepawhill.contentment.actor.Actor;
import org.geepawhill.contentment.core.Sequence;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.geometry.Point;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.jfx.JfxUtility;
import org.geepawhill.contentment.step.BoundsStep;
import org.geepawhill.contentment.step.Entrance;
import org.geepawhill.contentment.step.HandOvalStep;
import org.geepawhill.contentment.step.LettersStep;
import org.geepawhill.contentment.timing.RelativeTiming;
import org.geepawhill.contentment.timing.TimingBuilder;
import org.geepawhill.contentment.utility.Names;

import javafx.scene.Group;
import javafx.scene.text.Text;

public class OvalText implements Actor
{
	final String nickname;
	final String source;

	private final Group group;
	private Point center;

	private HandOvalStep ovalStep;
	private Format format;
	private LettersStep lettersStep;

	public OvalText(String source, Point center, Format format)
	{
		this.nickname = Names.make(getClass());
		this.center = center;
		this.source = source;
		this.format = format;
		lettersStep = new LettersStep(new RelativeTiming(.6d), source, center, format);
		ovalStep = new HandOvalStep(new RelativeTiming(.4d), format);
		this.group = JfxUtility.makeGroup(this, lettersStep.text, ovalStep.shape());
	}
	
	
	public String nickname()
	{
		return nickname;
	}

	public void sketch(Sequence sequence, double ms)
	{
		new TimingBuilder().build(ms, lettersStep, ovalStep);
		sequence.add(new Entrance(this));
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

}
