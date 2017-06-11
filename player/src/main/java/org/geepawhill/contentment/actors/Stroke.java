package org.geepawhill.contentment.actors;

import org.geepawhill.contentment.actor.Actor;
import org.geepawhill.contentment.core.Sequence;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.step.AddNodeStep;
import org.geepawhill.contentment.step.BezierStep;
import org.geepawhill.contentment.timing.Timing;
import org.geepawhill.contentment.utility.Names;

import javafx.scene.Group;

public class Stroke implements Actor
{
	private final String nickname;
	public final BezierStep step;
	private final Group group;

	public Stroke(PointPair points, Format format)
	{
		this.nickname = Names.make(getClass());
		this.step = new BezierStep(Timing.weighted(1d), format, points);
		this.group = new Group(step.shape());
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

	@Override
	public Sequence draw(double ms)
	{
		return new Sequence().add(new AddNodeStep(group,step)).add(step).schedule(ms);
	}

}
