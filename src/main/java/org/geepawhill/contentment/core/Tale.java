package org.geepawhill.contentment.core;

import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class Tale implements Actor
{
	private Group group;
	private String value;
	private double fromY;
	private Text text;
	private Rectangle rectangle;

	private final double XMARGIN = 20d;
	private final double XINSET = 30d;
	private final double YINSET = 20d;

	private final double GREEN = (double) 0xee / (double) 0xff;
	private final double BLUE = (double) 0xbb / (double) 0xff;

	public Tale(String value, double fromY)
	{
		this.value = value;
		this.fromY = fromY;
		this.group = new Group();
		text = new Text(800d, fromY + YINSET, value);
		rectangle = new Rectangle(XMARGIN, fromY, 1600d - XMARGIN - XINSET, 30d + 2 * YINSET);
		group.getChildren().addAll(rectangle,text);

		text.setTextOrigin(VPos.TOP);
		text.setTextAlignment(TextAlignment.CENTER);
		text.setFont(new Font("Tahoma",40d));
		text.setTextOrigin(VPos.TOP);
		text.setX(800d-text.getBoundsInLocal().getWidth()/2d);
		text.setY(fromY+YINSET);
		text.setStrokeWidth(3d);
		text.setStrokeLineCap(StrokeLineCap.ROUND);
		
		rectangle.setHeight(text.getBoundsInLocal().getHeight()+2*YINSET);
		rectangle.setStrokeWidth(2d);
		rectangle.setFill(Color.color(1d,GREEN,BLUE));
		rectangle.setStroke(Color.color(.1d,.1d,.1d));
		rectangle.setArcHeight(40d);
		rectangle.setArcWidth(40d);	
	}

	@Override
	public Group group()
	{
		return group;
	}
	
	public Step show()
	{
		return new ShowStep(group);
	}
	

}