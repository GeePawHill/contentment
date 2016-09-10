package org.geepawhill.contentment.core;

import org.geepawhill.contentment.geometry.ArrowPoints;
import org.geepawhill.contentment.geometry.Point;
import org.geepawhill.contentment.geometry.PointPair;

public class PointArrowComputer implements ArrowComputer
{

	private Point from;
	private Point to;

	public PointArrowComputer(Point from, Point to)
	{
		this.from = from;
		this.to = to;

	}

	@Override
	public ArrowPoints compute()
	{
		double d = 14;
		double h = 10;
		PointPair main = new PointPair(from, to);
		double dx = from.xDistance(to);
		double dy = from.yDistance(to);
		double distance = Math.sqrt(dx * dx + dy * dy);
		double xm = distance - d;
		double ym = h;
		double xn = xm;
		double yn = -h;
		double x;
		double sin = dy / distance;
		double cos = dx / distance;

		x = xm * cos - ym * sin + from.x;
		ym = xm * sin + ym * cos + from.y;
		xm = x;
		PointPair top = new PointPair(to, new Point(xm, ym));

		x = xn * cos - yn * sin + from.x;
		yn = xn * sin + yn * cos + from.y;
		xn = x;
		PointPair bottom = new PointPair(to, new Point(xn, yn));
		return new ArrowPoints(main, top, bottom);
	}
}
