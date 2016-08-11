package org.geepawhill.contentment.core;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;

public class Style {
	private StyleId id;
	private StyleApplier applier;
	
	@FunctionalInterface
	public static interface StyleApplier
	{
		void accept(Shape shape);
	}

	
	public Style(StyleId id, StyleApplier applier)
	{
		this.id = id;
		this.applier = applier;
	}

	public StyleId id()
	{
		return id;
	}
	
	public void apply(Shape node)
	{
		applier.accept(node);
	}
	
	static public Style penWidth(double width)
	{
		return new Style(StyleId.PenWidth,(shape) -> { shape.setStrokeWidth(width); });
	}
	
	static public Style lineColor(Paint paint)
	{
		return new Style(StyleId.LineColor,(shape) -> { shape.setStroke(paint); });
	}
}
