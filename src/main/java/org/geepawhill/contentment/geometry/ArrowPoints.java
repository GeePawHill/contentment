package org.geepawhill.contentment.geometry;

public class ArrowPoints
{
	public PointPair main;
	public PointPair top;
	public PointPair bottom;
	
	public ArrowPoints(PointPair points)
	{
		double d = 14;
		double h = 10;
		main = points;
		double dx = points.from.xDistance(points.to);
		double dy = points.from.yDistance(points.to);
		double distance = Math.sqrt(dx * dx + dy * dy);
		double xm = distance - d;
		double ym = h;
		double xn = xm;
		double yn = -h;
		double x;
		double sin = dy / distance;
		double cos = dx / distance;

		x = xm * cos - ym * sin + points.from.x;
		ym = xm * sin + ym * cos + points.from.y;
		xm = x;
		top = new PointPair(points.to,new Point(xm,ym));

		x = xn * cos - yn * sin + points.from.x;
		yn = xn * sin + yn * cos + points.from.y;
		xn = x;
		bottom = new PointPair(points.to,new Point(xn,yn));
	}
}
