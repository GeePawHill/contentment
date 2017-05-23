package org.geepawhill.contentment.step;

import javafx.geometry.VPos;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class SlideLine
{
	enum Layout  { LEFT, RIGHT, CENTER, INDENT };

	public final Text text;
	public final Layout layout;
	
	static public final String BASE_FONT = "Buxton Sketch";
	
	public SlideLine(String line, int start, double size, Paint paint, Layout layout)
	{
		text = new Text(line.substring(start));
		text.setTextOrigin(VPos.TOP);
//		text.setFont(new Font("Samuels hand bold", size));
//		text.setFont(new Font("Caveat Brush", size));
//		text.setFont(new Font("Gloria Hallelujah", size));
//		text.setFont(new Font("GoodDog", size));
//		text.setFont(Font.font("GoodDog", FontWeight.NORMAL, FontPosture.ITALIC,size));
//		text.setFont(new Font("WCManoNegraBta-Bold", size));
//		text.setFont(new Font("BelligerentMadness", size));
		text.setFont(new Font(BASE_FONT,size));
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
