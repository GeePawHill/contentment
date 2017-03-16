package org.geepawhill.contentment.style;

import org.geepawhill.contentment.format.Style;
import org.geepawhill.contentment.format.StyleApplier;

import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class TextFont
{
	public static final String KEY = "TextFont";

	static public Style font(String nickname, Font font, Double width, Double opacity)
	{
		StyleApplier applier = new StyleApplier()
		{
			@Override
			public void apply(Shape shape)
			{
				if (shape instanceof Text)
				{
					Text text = (Text) shape;
					text.setFont(font);
					text.setStrokeWidth(width);
					text.setOpacity(opacity);
				}
			}
		};
		return new Style(KEY, nickname, applier, font.getFamily() + " " + font.getSize());
	}

	public static Style mediumHand()
	{
		return font("MediumHand", new Font("Buxton Sketch", 30d), 1d, .5d);
	}

	public static Style unspecified()
	{
		return font("Unspecified", new Font(50d), 1d, .5d);
	}

	public static Style largeHand()
	{
		return font("LargeHand", new Font("Buxton Sketch", 60d), 2d, .5d);
	}
	
}
