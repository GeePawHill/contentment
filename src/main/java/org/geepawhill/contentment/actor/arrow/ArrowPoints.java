package org.geepawhill.contentment.actor.arrow;

import org.geepawhill.contentment.geometry.PointPair;

public class ArrowPoints
{
	public PointPair main;
	public PointPair top;
	public PointPair bottom;
	
	public ArrowPoints(PointPair main, PointPair top, PointPair bottom)
	{
		this.main = main;
		this.top = top;
		this.bottom = bottom;
	}
}
