package org.geepawhill.contentment.style;

public class PenWidth
{

	static public Style penWidth(double width)
	{
		return new Style(Double.toString(width), StyleId.PenWidth, (shape) -> {
			shape.setStrokeWidth(width);
		});
	}

}
