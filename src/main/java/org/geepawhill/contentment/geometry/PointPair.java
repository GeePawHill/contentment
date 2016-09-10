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

	public PointPair(double fromX, double fromY, double toX, double toY)
	{
		this(new Point(fromX, fromY), new Point(toX, toY));
	}

	public PointPair(Bounds bounds)
	{
		this(new Point(bounds.getMinX(), bounds.getMinY()), new Point(bounds.getMaxX(), bounds.getMaxY()));
	}

	public PointPair(Node node)
	{
		this(node.getBoundsInParent());
	}

	public Bounds asBounds()
	{
		return new BoundingBox(from.x, from.y, to.x - from.x, to.y - from.y);
	}

	public Point center()
	{
		return new Point(centerX(), centerY());
	}

	public double centerY()
	{
		return (from.y + to.y) / 2d;
	}

	public double centerX()
	{
		return (from.x + to.x) / 2d;
	}

	public Point north()
	{
		return new Point(centerX(), from.y);
	}

	public Point south()
	{
		return new Point(centerX(), to.y);
	}

	public Point east()
	{
		return new Point(to.x, centerY());
	}

	public Point west()
	{
		return new Point(from.x, centerY());
	}

	public Point northeast()
	{
		return new Point(from.x, to.y);
	}

	public Point northwest()
	{
		return new Point(from.x, from.y);
	}

	public Point southeast()
	{
		return new Point(to.x, to.y);
	}

	public Point southwest()
	{
		return new Point(from.x, to.y);
	}

	public double partialX(double fraction)
	{
		return from.x + (to.x - from.x) * fraction;
	}

	public double partialY(double fraction)
	{
		return from.y + (to.y - from.y) * fraction;
	}

	public Point intersects(PointPair other)
	{
		Point result = null;

		double s1_x = to.x - from.x;
		double s1_y = to.y - from.y;
		double s2_x = other.to.x - other.from.x;
		double s2_y = other.to.y - other.from.y;
		double s = (-s1_y * (from.x - other.from.x) + s1_x * (from.y - other.from.y)) / (-s2_x * s1_y + s1_x * s2_y);
		double t = (s2_x * (from.y - other.from.y) - s2_y * (from.x - other.from.x)) / (-s2_x * s1_y + s1_x * s2_y);

		if (s >= 0 && s <= 1 && t >= 0 && t <= 1)
		{
			result = new Point(from.x + (t * s1_x), from.y + (t * s1_y));
		}

		return result;
	}

	public Point quadIntersects(PointPair other)
	{
		PointPair northLine = new PointPair(from.x, from.y, to.x, from.y);
		Point northIntersect = northLine.intersects(other);
		if (northIntersect != null) return northIntersect;
		PointPair southLine = new PointPair(from.x, to.y, to.x, to.y);
		Point southIntersect = southLine.intersects(other);
		if (southIntersect != null) return southIntersect;
		PointPair eastLine = new PointPair(to.x, from.y, to.x, to.y);
		Point eastIntersect = eastLine.intersects(other);
		if (eastIntersect != null) return eastIntersect;
		PointPair westLine = new PointPair(from.x, from.y, to.x, from.y);
		Point westIntersect = westLine.intersects(other);
		if (westIntersect != null) return westIntersect;
		return null;
	}

	public PointPair grow(double delta)
	{
		return new PointPair(from.x-delta,from.y-delta,to.x+delta,to.y+delta);
	}

}
