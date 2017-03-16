package org.geepawhill.contentment.style;

import org.geepawhill.contentment.format.Style;
import org.geepawhill.contentment.format.StyleApplier;

import javafx.scene.shape.Shape;

public class Opacity
{
	
	public final static String KEY="Opacity";

	public static Style opacity(double d)
	{
		StyleApplier applier = new StyleApplier() {
			@Override
			public void apply(Shape shape)
			{
				shape.setOpacity(d);
			} 
		};
		return new Style("Opacity","", applier, Double.toString(d));
	}

}
