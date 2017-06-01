package org.geepawhill.contentment.actors;

import org.geepawhill.contentment.actor.Actor;
import org.geepawhill.contentment.core.Sequence;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.step.EntranceStep;
import org.geepawhill.contentment.step.StrokeStep;
import org.geepawhill.contentment.timing.RelativeTiming;
import org.geepawhill.contentment.timing.Timing;
import org.geepawhill.contentment.utility.Names;

import javafx.scene.Group;

public class Stroke implements Actor
{
	private final String nickname;
	public final StrokeStep step;
	private final Group group;

	public Stroke(PointPair points, Format format)
	{
		this.nickname = Names.make(getClass());
		this.step = new StrokeStep(new RelativeTiming(1d), points, format);
		this.group = new Group(step.shape());
	}

	public Sequence sketch(Sequence sequence, Timing timing)
	{
		if (sequence == null) sequence = new Sequence();
		sequence.add(new EntranceStep(this));
		sequence.add(step);
		sequence.schedule(timing.getAbsolute());
		return sequence;
	}

	@Override
	public String nickname()
	{
		return nickname;
	}

	@Override
	public Group group()
	{
		return group;
	}

}
