package org.geepawhill.contentment.position;
import org.geepawhill.contentment.atom.GroupSource;
import org.geepawhill.contentment.atom.NodeSource;
import org.geepawhill.contentment.geometry.Point;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.utility.JfxUtility;

import javafx.scene.Node;

public class BelowRight implements Position
{
	
	private NodeSource anchor;

	public BelowRight(NodeSource anchor)
	{
		this.anchor = anchor;
	}

	
	@Override
	public void position(Node node, PointPair dimensions)
	{
		Point anchorPoint = new PointPair(anchor.get().getBoundsInParent()).southeast();
		JfxUtility.setTopAlignment(node);
		node.setTranslateX(anchorPoint.x-dimensions.width());
		node.setTranslateY(anchorPoint.y);
	}

}
