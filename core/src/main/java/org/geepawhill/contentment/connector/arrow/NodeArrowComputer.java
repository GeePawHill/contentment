package org.geepawhill.contentment.connector.arrow;

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
		double xDistance = from.xDistance(to);
		double yDistance = from.yDistance(to);
		double distance = Math.sqrt(xDistance * xDistance + yDistance * yDistance);
		double xm = distance - d;
		double ym = h;
		double xn = xm;
		double yn = -h;
		double sin = yDistance / distance;
		double cos = xDistance / distance;

		double x = xm * cos - ym * sin + from.x;
		ym = xm * sin + ym * cos + from.y;
		xm = x;
		PointPair toTop = new PointPair(to, new Point(xm, ym));

		x = xn * cos - yn * sin + from.x;
		yn = xn * sin + yn * cos + from.y;
		xn = x;
		PointPair toBottom = new PointPair(to, new Point(xn, yn));
		
		xDistance = to.xDistance(from);
		yDistance = to.yDistance(from);
		distance = Math.sqrt(xDistance * xDistance + yDistance * yDistance);
		xm = distance - d;
		ym = h;
		xn = xm;
		yn = -h;
		sin = yDistance / distance;
		cos = xDistance / distance;
	
		x = xm * cos - ym * sin + to.x;
		ym = xm * sin + ym * cos + to.y;
		xm = x;
		PointPair fromTop = new PointPair(from, new Point(xm, ym));

		x = xn * cos - yn * sin + to.x;
		yn = xn * sin + yn * cos + to.y;
		xn = x;
		PointPair fromBottom = new PointPair(from, new Point(xn, yn));

		return new ArrowPoints(main, toTop, toBottom, fromTop, fromBottom);

	}


}
