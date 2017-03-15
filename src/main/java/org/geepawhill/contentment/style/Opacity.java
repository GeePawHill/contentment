package org.geepawhill.contentment.style;

import org.geepawhill.contentment.core.Style;
import org.geepawhill.contentment.core.StyleApplier;

import javafx.scene.shape.Shape;

public class Opacity
{

	public static Style opacity(double d)
	{
		StyleApplier applier = new StyleApplier() {
			@Override
			public void apply(Shape shape)
			{
				shape.setOpacity(d);
			} 
		};
		return new Style("Opacity",Double.toString(d), applier, Double.toString(d));
	}

}
