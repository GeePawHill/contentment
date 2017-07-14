package org.geepawhill.contentment.atom;

import org.geepawhill.contentment.actor.Actor;
import org.geepawhill.contentment.core.Atom;
import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.geometry.Point;
import org.geepawhill.contentment.utility.JfxUtility;

import javafx.scene.shape.Circle;

public class SpotAtom implements Atom
{
	private Circle circle;
	private Actor actor;

	public SpotAtom(Actor actor,Point center)
	{
		this.actor = actor;
		this.circle = new Circle(center.x,center.y,0d);

	}

	@Override
	public void setup(Context context)
	{
		JfxUtility.addIfNeeded(actor.group(), circle);
	}

	@Override
	public void partial(Context context, double fraction)
	{
	}

}
