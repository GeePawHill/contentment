package org.geepawhill.contentment.style;

import org.geepawhill.contentment.core.Style;
import org.geepawhill.contentment.core.StyleApplier;
import org.geepawhill.contentment.core.StyleId;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;

public class LineColor
{

	static public Style lineColor(String nickname, Paint paint)
	{
		StyleApplier applier = new StyleApplier() {
			@Override
			public void apply(Shape shape)
			{
				shape.setStroke(paint);
				if(shape instanceof Text) ((Text)shape).setFill(paint);
			} 
		};
		nickname = nickname+" ("+paint.toString()+")";
		return new Style(nickname, StyleId.ShapeLinePaint, applier);
	}
	
	static public Style black()
	{
		return lineColor("BLACK",Color.BLACK);
	}

	public static Style red()
	{
		return lineColor("RED",Color.RED);
	}

}
