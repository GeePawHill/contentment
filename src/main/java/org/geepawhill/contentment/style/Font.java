package org.geepawhill.contentment.style;

import org.geepawhill.contentment.core.Style;
import org.geepawhill.contentment.core.StyleApplier;
import org.geepawhill.contentment.core.StyleId;

import javafx.scene.shape.Shape;
import javafx.scene.text.Text;

public class Font
{
	static public Style font(javafx.scene.text.Font font)
	{
		StyleApplier applier = new StyleApplier() {
			@Override
			public void apply(Shape shape)
			{
				if(shape instanceof Text) ((Text)shape).setFont(font);
			} 
		};
		return new Style(font.getFamily()+" "+font.getSize(),StyleId.Font, applier, font.getFamily()+" "+font.getSize());
	}

}
