package org.geepawhill.contentment.style;

import org.geepawhill.contentment.format.Style;
import org.geepawhill.contentment.format.StyleApplier;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;

public class TextColors
{
	public static final String KEY = "TextColor";
	
	public static Style color(String nickname,Paint both, Double opacity)
	{
		return color(nickname,both,both,opacity);
	}

	
	public static Style color(String nickname,Paint stroke, Paint fill, Double opacity)
	{
		StyleApplier applier = new StyleApplier() {
			@Override
			public void apply(Shape shape)
			{
				shape.setStroke(stroke);
				shape.setFill(fill);
				shape.setOpacity(opacity);
			} 
		};
		String value = "Stroke: "+stroke.toString()+" Fill: "+fill.toString()+" Opacity: "+opacity;
		return new Style(KEY, nickname, applier, value);
		
	}
	
	public static Style unspecified()
	{
		return color("Unspecified",Color.BLACK,Color.RED,1d);
	}

}
