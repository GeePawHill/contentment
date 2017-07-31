package org.geepawhill.contentment.position;
import org.geepawhill.contentment.atom.GroupSource;
import org.geepawhill.contentment.geometry.Point;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.utility.JfxUtility;

import javafx.scene.Node;

public class BelowCenter implements Position
{
	
	private GroupSource anchor;
	private double offset;

	public BelowCenter(GroupSource anchor,double offset)
	{
		this.anchor = anchor;
		this.offset = offset;
	}
	
	public BelowCenter(GroupSource anchor)
	{
		this(anchor,0d);
	}
	
	@Override
	public void position(Node node, PointPair dimensions)
	{
		Point anchorPoint = new PointPair(anchor.get().getBoundsInParent()).south();
		JfxUtility.setTopAlignment(node);
		node.setTranslateX(anchorPoint.x-dimensions.width()/2d);
		node.setTranslateY(anchorPoint.y+offset);
	}

}
