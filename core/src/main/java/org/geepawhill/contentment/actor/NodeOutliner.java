package org.geepawhill.contentment.actor;
import org.geepawhill.contentment.outline.ValueTree;

import javafx.geometry.Bounds;
import javafx.scene.shape.Shape;

public class NodeOutliner 
{
	private final Actor actor;
	private final ValueTree outline;
	
	public NodeOutliner(Actor actor,ValueTree outline)
	{
		this.actor = actor;
		this.outline = outline;
	}
	
	public void start()
	{
		outline.add(actor.nickname());
		outline.indent();
	}
	
	public void end()
	{
		outline.dedent();
	}
	
	public void startNode(Shape node)
	{
		outline.add(node.getClass().getSimpleName());
		outline.indent();
	}
	
	public void endNode()
	{
		outline.dedent();
	}
	
	public boolean visibility(Shape node)
	{
		Boolean visible = node.isVisible();
		outline.add("Visible",Boolean.toString(visible));
		return visible;
	}
	
	public void bounds(Shape node)
	{
		Bounds bounds = node.getBoundsInParent();
		String fancy = String.format("(%.0f,%.0f) - (%.0f,%.0f)", bounds.getMinX(),bounds.getMinY(),bounds.getMaxX(),bounds.getMaxY());
		outline.add("Bounds",fancy);
	}

	public void opacity(Shape node)
	{
		outline.add("Opacity",Double.toString(node.getOpacity()));
	}
	
	public void strokeWidth(Shape node)
	{
		outline.add("StrokeWidth",Double.toString(node.getStrokeWidth()));
	}
	
	public void lineColor(Shape node)
	{
		outline.add("LineColor",node.getStroke().toString());
	}
	
	public void add(String key,String value)
	{
		outline.add(key,value);
	}
}
