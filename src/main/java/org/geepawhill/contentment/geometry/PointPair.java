package org.geepawhill.contentment.geometry;

import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.scene.Node;

public class PointPair
{
	public final Point from;
	public final Point to;

	public PointPair(Point from, Point to)
	{
		this.from = from;
		this.to = to;
	}
	
	public PointPair(double fromX,double fromY,double toX,double toY)
	{
		this(new Point(fromX,fromY),new Point(toX,toY));
	}
	
	public PointPair(Bounds bounds)
	{
		this(
			new Point(bounds.getMinX(),bounds.getMinY()),
			new Point(bounds.getMaxX(),bounds.getMaxY())
		);
	}
	
	public PointPair(Node node)
	{
		this(node.getBoundsInParent());
	}
	
	public Bounds asBounds()
	{
		return new BoundingBox(from.x,from.y,to.x-from.x,to.y-from.y);
	}
	
	public Point center()
	{
		return new Point(centerX(),centerY());
	}

	public double centerY()
	{
		return (from.y+to.y)/2d;
	}

	public double centerX()
	{
		return (from.x+to.x)/2d;
	}
	
	public Point north()
	{
		return new Point(centerX(),from.y);
	}
	
	public Point south()
	{
		return new Point(centerX(),to.y);
	}
	
	public Point east()
	{
		return new Point(to.x,centerY());
	}
	
	public Point west()
	{
		return new Point(from.x,centerY());
	}
	
	public Point northeast()
	{
		return new Point(from.x,to.y);
	}
	
	public Point northwest()
	{
		return new Point(from.x,from.y);
	}
	
	public Point southeast()
	{
		return new Point(to.x,to.y);
	}
	
	public Point southwest()
	{
		return new Point(from.x,to.y);
	}

	public double partialX(double fraction)
	{
		return from.x + (to.x - from.x) * fraction;
	}

	public double partialY(double fraction)
	{
		return from.y + (to.y - from.y) * fraction;
	}
}
