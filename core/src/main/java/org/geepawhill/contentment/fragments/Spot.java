package org.geepawhill.contentment.fragments;

import org.geepawhill.contentment.core.*;
import org.geepawhill.contentment.geometry.Point;
import org.geepawhill.contentment.utility.JfxUtility;

import javafx.scene.shape.Circle;

public class Spot implements Fragment
{
	private Circle circle;
	private GroupSource actor;

	public Spot(GroupSource actor,Point center)
	{
		this.actor = actor;
		this.circle = new Circle(center.x,center.y,0d);

	}

	@Override
	public void prepare(Context context)
	{
	}

	@Override
	public boolean interpolate(Context context, double fraction)
	{
		JfxUtility.addIfNeeded(actor, circle);
		return false;
	}

}
