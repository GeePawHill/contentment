package org.geepawhill.contentment.style;

import org.geepawhill.contentment.format.Style;
import org.geepawhill.contentment.format.StyleApplier;

import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Typeface
{
	public static final String KEY = "Font";

	static public Style font(String nickname, Font font)
	{
		StyleApplier applier = new StyleApplier() {
			@Override
			public void apply(Shape shape)
			{
				if(shape instanceof Text) ((Text)shape).setFont(font);
			} 
		};
		return new Style(KEY,nickname, applier, font.getFamily()+" "+font.getSize());
	}

}
