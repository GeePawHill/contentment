package org.geepawhill.contentment.actor;
import org.geepawhill.contentment.core.Actor;
import org.geepawhill.contentment.outline.KvOutline;

import javafx.geometry.Bounds;
import javafx.scene.shape.Shape;

public class ActorOutliner 
{
	private final Actor actor;
	private final KvOutline outline;
	
	public ActorOutliner(Actor actor,KvOutline outline)
	{
		this.actor = actor;
		this.outline = outline;
	}
	
	public void start()
	{
		outline.append(actor.nickname());
		outline.indent();
	}
	
	public void end()
	{
		outline.dedent();
	}
	
	public void startNode(Shape node)
	{
		outline.append(node.getClass().getSimpleName());
		outline.indent();
	}
	
	public void endNode()
	{
		outline.dedent();
	}
	
	public boolean visibility(Shape node)
	{
		Boolean visible = node.isVisible();
		outline.append("Visible",visible);
		return visible;
	}
	
	public void bounds(Shape node)
	{
		
		Bounds bounds = node.getBoundsInParent();
		String fancy = String.format("(%.0f,%.0f) - (%.0f,%.0f)", bounds.getMinX(),bounds.getMinY(),bounds.getMaxX(),bounds.getMaxY());
		outline.append("Bounds",fancy);
	}

	public void opacity(Shape node)
	{
		outline.append("Opacity",node.getOpacity());
	}
	
	public void strokeWidth(Shape node)
	{
		outline.append("StrokeWidth",node.getStrokeWidth());
	}
	
	public void lineColor(Shape node)
	{
		outline.append("LineColor",node.getStroke().toString());
	}
}
