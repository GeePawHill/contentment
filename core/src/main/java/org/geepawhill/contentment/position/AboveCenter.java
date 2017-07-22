package org.geepawhill.contentment.position;
import org.geepawhill.contentment.actor.Actor;
import org.geepawhill.contentment.geometry.Point;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.utility.JfxUtility;

import javafx.scene.Node;

public class AboveCenter implements Position
{
	
	private Actor anchor;
	private double offset;

	public AboveCenter(Actor anchor,double offset)
	{
		this.anchor = anchor;
		this.offset = offset;
	}
	
	public AboveCenter(Actor anchor)
	{
		this(anchor,0d);
	}
	
	@Override
	public void position(Node node, PointPair dimensions)
	{
		Point anchorPoint = new PointPair(anchor.group().getBoundsInParent()).north();
		JfxUtility.setTopAlignment(node);
		node.setTranslateX(anchorPoint.x-dimensions.width()/2d);
		node.setTranslateY(anchorPoint.y-dimensions.height()+offset);
	}

}
