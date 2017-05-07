package org.geepawhill.contentment.step;

import javafx.geometry.VPos;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class SlideLine
{
	enum Layout  { LEFT, RIGHT, CENTER, INDENT };

	public final Text text;
	public final Layout layout;
	
	public SlideLine(String line, int start, double size, Paint paint, Layout layout)
	{
		text = new Text(line.substring(start));
		text.setTextOrigin(VPos.TOP);
		text.setFont(new Font("Buxton Sketch", size));
		text.setStroke(paint);
		text.setFill(paint);
		this.layout = layout;
	}

	public double setAndIncrementY(double y)
	{
		text.setY(y);
		return text.getBoundsInParent().getMaxY();
	}
}
