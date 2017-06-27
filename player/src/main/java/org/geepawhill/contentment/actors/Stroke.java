package org.geepawhill.contentment.actors;

import org.geepawhill.contentment.actor.Actor;
import org.geepawhill.contentment.fast.AddNode;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.step.BezierStep;
import org.geepawhill.contentment.step.Step;
import org.geepawhill.contentment.step.Timed;
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
	public Step draw(double ms)
	{
		Timed sequence = new Timed(ms);
		sequence.add(new AddNode(group,step));
		sequence.add(step);
		return sequence;
	}

}
