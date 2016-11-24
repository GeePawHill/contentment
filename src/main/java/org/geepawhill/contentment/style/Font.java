package org.geepawhill.contentment.style;

import javafx.scene.shape.Shape;
import javafx.scene.text.Text;

public class Font
{
	static public Style font(javafx.scene.text.Font font)
	{
		Style.ShapeApplier applier = new Style.ShapeApplier() {
			@Override
			public void apply(Shape shape)
			{
				if(shape instanceof Text) ((Text)shape).setFont(font);
			} 
		};
		return new Style(font.getFamily()+" "+font.getSize(),StyleId.Font, applier);
	}

}
