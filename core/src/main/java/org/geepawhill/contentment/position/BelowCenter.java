package org.geepawhill.contentment.position;
import org.geepawhill.contentment.actor.Actor;
import org.geepawhill.contentment.geometry.Point;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.utility.JfxUtility;

import javafx.scene.Node;

public class BelowCenter implements Position
{
	
	private Actor anchor;
	private double offset;

	public BelowCenter(Actor anchor,double offset)
	{
		this.anchor = anchor;
		this.offset = offset;
	}
	
	public BelowCenter(Actor anchor)
	{
		this(anchor,0d);
	}
	
	@Override
	public void position(Node node, PointPair dimensions)
	{
		Point anchorPoint = new PointPair(anchor.group().getBoundsInParent()).south();
		JfxUtility.setTopAlignment(node);
		node.setTranslateX(anchorPoint.x-dimensions.width()/2d);
		node.setTranslateY(anchorPoint.y+offset);
	}

}
