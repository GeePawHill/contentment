package org.geepawhill.contentment.actors;

import org.geepawhill.contentment.actor.Actor;
import org.geepawhill.contentment.actor.GenericAgentBuilder;
import org.geepawhill.contentment.atom.BezierAtom;
import org.geepawhill.contentment.core.Atom;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.step.AtomStep;
import org.geepawhill.contentment.step.Step;
import org.geepawhill.contentment.timing.Timing;
import org.geepawhill.contentment.utility.Names;

import javafx.scene.Group;

public class Stroke implements Actor<GenericAgentBuilder<Stroke>>
{
	private final String nickname;
	private final Group group;
	private final Atom atom;

	public Stroke(PointPair points, Format format)
	{
		this.nickname = Names.make(getClass());
		this.group = new Group();
		this.atom = new BezierAtom(this,format,points);
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
		return new AtomStep(Timing.ms(ms),atom);
	}

	@Override
	public GenericAgentBuilder<Stroke> builder()
	{
		return new GenericAgentBuilder<>(this);
	}

}
