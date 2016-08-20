package org.geepawhill.contentment.core;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;

public class Style
{
	
	@FunctionalInterface
	public static interface ShapeApplier
	{
		public void apply(Shape shape);
	}

	public final StyleId id;
	private final ShapeApplier applier;

	private Style(StyleId id,ShapeApplier applier)
	{
		this.id=id;
		this.applier = applier;
	}
	
	static public Style lineColor(Paint paint)
	{
		return new Style(StyleId.LineColor,(shape)->{ shape.setStroke(paint);});
	}
	
	static public Style penWidth(double	width)
	{
		return new Style(StyleId.PenWidth,(shape)->{ shape.setStrokeWidth(width); });
	}
	
	@Override
	public String toString()
	{
		return id.toString()+": "+applier.toString();
	}

	public void apply(Shape shape)
	{
		applier.apply(shape);
		
	}

}
