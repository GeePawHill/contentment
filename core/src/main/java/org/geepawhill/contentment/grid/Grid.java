package org.geepawhill.contentment.grid;

import org.geepawhill.contentment.geometry.PointPair;

public class Grid
{
	private final PointPair bounds;

	public Grid(PointPair bounds)
	{
		this.bounds = bounds;
	}
	
	public Horizontal top()
	{
		return new Horizontal(bounds,0);
	}
	
	public Horizontal bottom()
	{
		return new Horizontal(bounds, 100);
	}

	public Vertical left()
	{
		return new Vertical(bounds,0);
	}

	public Vertical right()
	{
		return new Vertical(bounds,100);
	}

}
