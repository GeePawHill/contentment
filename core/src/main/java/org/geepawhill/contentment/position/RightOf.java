package org.geepawhill.contentment.position;
import org.geepawhill.contentment.core.GroupSource;
import org.geepawhill.contentment.geometry.Point;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.utility.JfxUtility;

import javafx.scene.Node;

public class RightOf implements Position
{
	
	private GroupSource anchor;
	private double offset;

	public RightOf(GroupSource anchor,double offset)
	{
		this.anchor = anchor;
		this.offset = offset;
	}
	
	public RightOf(GroupSource anchor)
	{
		this(anchor,0d);
	}
	
	@Override
	public void position(Node node, PointPair dimensions)
	{
		Point anchorPoint = new PointPair(anchor.get().getBoundsInParent()).east();
		JfxUtility.setTopAlignment(node);
		node.setTranslateX(anchorPoint.x+offset);
		node.setTranslateY(anchorPoint.y - dimensions.height()/2d);
	}

}
