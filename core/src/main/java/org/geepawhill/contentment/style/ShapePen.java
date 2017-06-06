package org.geepawhill.contentment.style;

import org.geepawhill.contentment.format.Style;
import org.geepawhill.contentment.format.StyleApplier;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;

public class ShapePen
{
	static public String KEY = "ShapePen";
	
	static public Style pen(String nickname, Paint line,Paint fill, double width, double opacity)
	{
		StyleApplier applier = new StyleApplier() {
			@Override
			public void apply(Shape shape)
			{
				shape.setStroke(line);
				shape.setFill(fill);
				shape.setStrokeWidth(width);
				shape.setOpacity(opacity);
			} 
		};
		String value = "Line: "+line.toString()+" Fill: "+fill.toString()+" Width: "+width+" Opacity: "+opacity;
		return new Style(KEY, applier, value);
	}

	public static Style first()
	{
		return pen("First", Color.RED, Color.RED, 5d, .5d);
	}
	
	public static Style second()
	{
		return pen("Second", Color.BLUE, Color.TRANSPARENT,5d,.5d);
	}

	public static Style third()
	{
		return pen("Third", Color.GREEN, Color.TRANSPARENT,5d,.5d);
	}

	public static Style fourth()
	{
		return pen("Fourth", Color.BLACK, Color.TRANSPARENT,5d,.5d);
	}
	
	public static Style thinFourth()
	{
		return pen("ThinFourth", Color.BLACK,Color.TRANSPARENT,2d,.5d);
	}

	public static Style thinFirst()
	{
		return pen("ThinFirst", Color.RED, Color.TRANSPARENT, 1d, .5d);
	}
	
}
