package org.geepawhill.contentment.geometry;

public class Bezier
{
	public final Point start;
	public final Point handle1;
	public final Point handle2;
	public final Point end;

	public Bezier(PointPair points)
	{
		this(points.from, points.to);
	}

	public Bezier(Point start, Point end)
	{
		PointPair pair = new PointPair(start, end);
		this.handle1 = pair.along(1d / 3d);
		this.handle2 = pair.along(2d / 3d);
		this.start = start;
		this.end = end;
	}

	public Bezier(Point start, Point handle1, Point handle2, Point end)
	{
		this.start = start;
		this.handle1 = handle1;
		this.handle2 = handle2;
		this.end = end;
	}

	public Point at(double t)
	{
		// A = -K0 + 3K1 + -3K2 + K3
		// B = 3K0 + -6K1 + 3K2
		// C = -3K0 + 3K1
		// D = K0

		double ax = -start.x + 3d * handle1.x - 3d * handle2.x + end.x;
		double ay = -start.y + 3d * handle1.y - 3d * handle2.y + end.y;
		double bx = 3d * start.x - 6d * handle1.x + 3d * handle2.x;
		double by = 3d * start.y - 6d * handle1.y + 3d * handle2.y;
		double cx = -3d * start.x + 3d * handle1.x;
		double cy = -3d * start.y + 3d * handle1.y;
		double dx = start.x;
		double dy = start.y;

		double x = ax * (t * t * t) + bx * (t * t) + cx * t + dx;
		double y = ay * (t * t * t) + by * (t * t) + cy * t + dy;
		return new Point(x, y);
	}

}
