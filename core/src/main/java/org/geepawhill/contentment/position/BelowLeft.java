package org.geepawhill.contentment.position;
import org.geepawhill.contentment.actor.Actor;
import org.geepawhill.contentment.atom.GroupSource;
import org.geepawhill.contentment.geometry.Point;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.utility.JfxUtility;

import javafx.scene.Node;

public class BelowLeft implements Position
{
	
	private GroupSource anchor;

	public BelowLeft(GroupSource anchor)
	{
		this.anchor = anchor;
	}
	
	@Override
	public void position(Node node, PointPair dimensions)
	{
		Point anchorPoint = new PointPair(anchor.get().getBoundsInParent()).southwest();
		JfxUtility.setTopAlignment(node);
		node.setTranslateX(anchorPoint.x);
		node.setTranslateY(anchorPoint.y);
	}

}
