package org.geepawhill.contentment.format;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;

public class TextPaint implements IStyle
{
	
	private final Paint stroke;
	private final Paint fill;
	private final double opacity;

	public TextPaint(Paint stroke,Paint fill,double opacity)
	{
		this.stroke = stroke;
		this.fill = fill;
		this.opacity = opacity;
		
	}

	@Override
	public void apply(Shape subject)
	{
		if(!(subject instanceof Text)) throw new RuntimeException("Attempt to apply TextPaint to non-Text.");
		Text text = (Text) subject;
		text.setFill(fill);
		text.setStroke(stroke);
		text.setOpacity(opacity);
	}

}
