package org.geepawhill.contentment.style;

import org.geepawhill.contentment.format.Style;
import org.geepawhill.contentment.format.StyleApplier;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class TypeFace
{
	public static final String FACE = "TextFont";
	public static final String COLOR = "TextColor";

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
		return new Style(FACE, applier, font.getFamily() + " " + font.getSize());
	}

	public static Style smallHand()
	{
		return font("SmallHand", new Font("Buxton Sketch", 22d), 1d, 1d);
	}

	public static Style mediumHand()
	{
		return font("MediumHand", new Font("Buxton Sketch", 30d), 1d, .5d);
	}

	public static Style largeHand()
	{
		return font("LargeHand", new Font("GoodDog", 60d), 2d, .5d);
	}

	public static Style mediumSans()
	{
		return font("MediumSans", new Font("Arial", 30d), 1d, .8d);
	}

	public static Style smallSans()
	{
		return font("SmallSans", new Font("Arial", 15d), 1d, .8d);
	}

	public static Style smallFixed()
	{
		return font("SmallFixed", new Font("Consolas", 15d), 1d, 1d);
	}

	public static Style color(String nickname, Paint both, Double opacity)
	{
		return TypeFace.color(nickname, both, both, opacity);
	}

	public static Style color(String nickname, Paint stroke, Paint fill, Double opacity)
	{
		StyleApplier applier = new StyleApplier()
		{
			@Override
			public void apply(Shape shape)
			{
				shape.setStroke(stroke);
				shape.setFill(fill);
				shape.setOpacity(opacity);
			}
		};
		String value = "Stroke: " + stroke.toString() + " Fill: " + fill.toString() + " Opacity: " + opacity;
		return new Style(COLOR, applier, value);

	}

	public static Style white()
	{
		return color("White", Color.WHITE, 1d);
	}
}
