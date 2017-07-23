package org.geepawhill.contentment.actors;

import org.geepawhill.contentment.actor.GenericActor;
import org.geepawhill.contentment.actor.ScriptWorld;
import org.geepawhill.contentment.atom.BezierAtom;
import org.geepawhill.contentment.core.Atom;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.step.AtomStep;
import org.geepawhill.contentment.step.Step;
import org.geepawhill.contentment.timing.Timing;
import org.geepawhill.contentment.utility.Names;

import javafx.scene.Group;

public class Stroke extends GenericActor
{
	private final String nickname;
	private final Group group;
	private final Atom atom;

	public Stroke(ScriptWorld world, PointPair points, Format format)
	{
		super(world);
		this.nickname = Names.make(getClass());
		this.group = new Group();
		this.atom = new BezierAtom(this,format,points);
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
}
