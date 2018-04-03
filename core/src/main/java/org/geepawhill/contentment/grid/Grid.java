package org.geepawhill.contentment.grid;

import org.geepawhill.contentment.geometry.*;

public class Grid
{
	private final PointPair bounds;

	public Grid(PointPair bounds)
	{
		this.bounds = bounds;
	}
	
	public Horizontal top()
	{
		return horizontal(0);
	}
	
	public Horizontal bottom()
	{
		return horizontal(100);
	}

	public Vertical left()
	{
		return vertical(0);
	}

	public Vertical right()
	{
		return vertical(100);
	}

	public Vertical vertical(double percent)
	{
		return new Vertical(bounds,percent);
	}

	public Horizontal horizontal(double percent)
	{
		return new Horizontal(bounds,percent);
	}

	public Point point(Vertical vertical, Horizontal horizontal)
	{
		return new Point(vertical.points().from.x,horizontal.points.from.y);
	}
	
	public PointPair area(Vertical fromX,Horizontal fromY,Vertical toX,Horizontal toY)
	{
		return new PointPair(
				fromX.points().from.x,
				fromY.points().from.y,
				toX.points().to.x,
				toY.points().to.y);
	}
}
