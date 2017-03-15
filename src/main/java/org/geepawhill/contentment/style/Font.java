package org.geepawhill.contentment.style;

import org.geepawhill.contentment.core.Style;
import org.geepawhill.contentment.core.StyleApplier;

import javafx.scene.shape.Shape;
import javafx.scene.text.Text;

public class Font
{
	public static final String KIND = "Font";

	static public Style font(javafx.scene.text.Font font)
	{
		StyleApplier applier = new StyleApplier() {
			@Override
			public void apply(Shape shape)
			{
				if(shape instanceof Text) ((Text)shape).setFont(font);
			} 
		};
		return new Style(KIND,font.getFamily()+" "+font.getSize(), applier, font.getFamily()+" "+font.getSize());
	}

}
