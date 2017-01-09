package org.geepawhill.contentment.style;

import org.geepawhill.contentment.core.Style;
import org.geepawhill.contentment.core.StyleId;

public class PenWidth
{

	static public Style penWidth(double width)
	{
		return new Style(Double.toString(width), StyleId.ShapeLineWidth, (shape) -> {
			shape.setStrokeWidth(width);
		});
	}

}
