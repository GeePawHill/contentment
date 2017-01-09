package org.geepawhill.contentment.style;

import org.geepawhill.contentment.core.Style;
import org.geepawhill.contentment.core.StyleApplier;
import org.geepawhill.contentment.core.StyleId;

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
		return new Style(Double.toString(d),StyleId.ShapeLineOpacity, applier);
	}

}
