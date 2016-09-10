package org.geepawhill.contentment.core;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Style
{

	@FunctionalInterface
	public static interface ShapeApplier
	{
		public void apply(Shape shape);
	}

	public final StyleId id;
	private final ShapeApplier applier;

	private Style(StyleId id, ShapeApplier applier)
	{
		this.id = id;
		this.applier = applier;
	}

	@Override
	public String toString()
	{
		return id.toString() + ": " + applier.toString();
	}

	public void apply(Shape shape)
	{
		applier.apply(shape);

	}

	static public Style lineColor(Paint paint)
	{
		ShapeApplier applier = new ShapeApplier() {
			@Override
			public void apply(Shape shape)
			{
				shape.setStroke(paint);
				if(shape instanceof Text) ((Text)shape).setFill(paint);
			} 
		};
		return new Style(StyleId.LineColor, applier);
	}

	static public Style penWidth(double width)
	{
		return new Style(StyleId.PenWidth, (shape) -> {
			shape.setStrokeWidth(width);
		});
	}

	static public Style font(Font font)
	{
		ShapeApplier applier = new ShapeApplier() {
			@Override
			public void apply(Shape shape)
			{
				if(shape instanceof Text) ((Text)shape).setFont(font);
			} 
		};
		return new Style(StyleId.Font,applier);
	}
	
	static public Style composite(Style... styles)
	{
		ShapeApplier applier = new ShapeApplier() {
			@Override
			public void apply(Shape shape)
			{
				for(Style style : styles) style.apply(shape);
			} 
		};
		return new Style(StyleId.Font,applier);
	}

	public static Style opacity(double d)
	{
		ShapeApplier applier = new ShapeApplier() {
			@Override
			public void apply(Shape shape)
			{
				shape.setOpacity(d);
			} 
		};
		return new Style(StyleId.Opacity,applier);
	}
	
	public static Style dash(Double... dash)
	{
		ShapeApplier applier = new ShapeApplier() {
			@Override
			public void apply(Shape shape)
			{
				shape.getStrokeDashArray().clear();
				shape.getStrokeDashArray().addAll(dash);
			} 
		};
		return new Style(StyleId.Dash,applier);		
	}

	public static Style nodash()
	{
		ShapeApplier applier = new ShapeApplier() {
			@Override
			public void apply(Shape shape)
			{
				shape.getStrokeDashArray().clear();;
			} 
		};
		return new Style(StyleId.Dash,applier);		
	}


}
