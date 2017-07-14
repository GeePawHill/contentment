package org.geepawhill.contentment.position;
import org.geepawhill.contentment.actor.Actor;
import org.geepawhill.contentment.geometry.Point;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.utility.JfxUtility;

import javafx.scene.Node;

public class Centered implements Position
{
	
	private Actor actor;
	private Point anchor;

	public Centered(Actor actor)
	{
		this(actor,null);
	}
	
	public Centered(Point anchor)
	{
		this(null,anchor);
	}
	
	public Centered(Actor actor,Point anchor)
	{
		this.actor = actor;
		this.anchor = anchor;
	}
	
	public Centered(double x,double y)
	{
		this(new Point(x,y));
	}

	@Override
	public void position(Node node, PointPair dimensions)
	{
		JfxUtility.setTopAlignment(node);
		if(actor!=null) anchor = new PointPair(actor.group().getBoundsInParent()).center();
		node.setTranslateX(anchor.x-dimensions.width()/2d);
		node.setTranslateY(anchor.y-dimensions.height()/2d);
	}

}