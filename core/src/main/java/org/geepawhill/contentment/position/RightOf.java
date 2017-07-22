package org.geepawhill.contentment.position;
import org.geepawhill.contentment.actor.Actor;
import org.geepawhill.contentment.geometry.Point;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.utility.JfxUtility;

import javafx.scene.Node;

public class RightOf implements Position
{
	
	private Actor anchor;
	private double offset;

	public RightOf(Actor anchor,double offset)
	{
		this.anchor = anchor;
		this.offset = offset;
	}
	
	public RightOf(Actor anchor)
	{
		this(anchor,0d);
	}
	
	@Override
	public void position(Node node, PointPair dimensions)
	{
		Point anchorPoint = new PointPair(anchor.group().getBoundsInParent()).east();
		JfxUtility.setTopAlignment(node);
		node.setTranslateX(anchorPoint.x+offset);
		node.setTranslateY(anchorPoint.y - dimensions.height()/2d);
	}

}
