package org.geepawhill.contentment.style;

import org.geepawhill.contentment.format.Style;
import org.geepawhill.contentment.format.StyleApplier;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;

public class Frames
{
	public static final String KEY = "Frame";
	
	
	public static Style frame(String nickname,Paint stroke, Double width, Double opacity)
	{
		return frame(nickname,stroke,Color.TRANSPARENT,width,opacity);
	}

	public static Style frame(String nickname,Paint stroke, Paint fill, Double width, Double opacity)
	{
		StyleApplier applier = new StyleApplier() {
			@Override
			public void apply(Shape shape)
			{
				shape.setStroke(stroke);
				shape.setFill(fill);
				shape.setStrokeWidth(width);
				shape.setOpacity(opacity);
			} 
		};
		String value = "Frame: "+stroke.toString()+" Fill: "+fill.toString()+" Opacity: "+opacity;
		return new Style(KEY, nickname, applier, value);
		
	}
	
	public static Style unspecified()
	{
		return frame("Unspecified",Color.WHITE,Color.TRANSPARENT, 1d, 1d);
	}

}
