package org.geepawhill.contentment.style;

import javafx.scene.shape.Shape;

public class Opacity
{

	public static Style opacity(double d)
	{
		Style.ShapeApplier applier = new Style.ShapeApplier() {
			@Override
			public void apply(Shape shape)
			{
				shape.setOpacity(d);
			} 
		};
		return new Style(Double.toString(d),StyleId.Opacity, applier);
	}

}
