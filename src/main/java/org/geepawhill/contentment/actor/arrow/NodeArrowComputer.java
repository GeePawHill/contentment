package org.geepawhill.contentment.actor.arrow;

import org.geepawhill.contentment.geometry.Point;
import org.geepawhill.contentment.geometry.PointPair;

import javafx.scene.Node;

public class NodeArrowComputer implements ArrowComputer
{
	
	private Node fromNode;
	private Node toNode;

	public NodeArrowComputer(Node fromNode,Node toNode)
	{
		this.fromNode = fromNode;
		this.toNode = toNode;
		
	}

	@Override
	public ArrowPoints compute()
	{
		double d = 14;
		double h = 10;
		Point fromCenter = new PointPair(fromNode).center();
		Point toCenter = new PointPair(toNode).center();
		PointPair startLine = new PointPair(fromCenter,toCenter);
		PointPair fromGrown = new PointPair(fromNode).grow(6d);
		PointPair toGrown = new PointPair(toNode).grow(6d);
		Point from = fromGrown.quadIntersects(startLine);
		Point to = toGrown.quadIntersects(startLine);
	
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
