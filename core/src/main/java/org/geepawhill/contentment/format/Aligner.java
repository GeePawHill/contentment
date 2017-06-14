package org.geepawhill.contentment.format;

import org.geepawhill.contentment.geometry.Point;

import javafx.geometry.HPos;
import javafx.scene.text.Text;

public class Aligner
{
	
	private HPos align;

	private Aligner(HPos align)
	{
		this.align = align;
	}

	public static Aligner align(HPos align)
	{
		return new Aligner(align);
	}
	
	public void align(Point point,Text text)
	{
		switch(align)
		{
			default:
			case CENTER:
				text.setX(point.x - text.getBoundsInParent().getWidth() / 2d);
				text.setY(point.y);
				break;
			case LEFT:
				text.setX(point.x);
				text.setY(point.y);
				break;
			case RIGHT:
				text.setX(point.x - text.getBoundsInParent().getWidth());
				text.setY(point.y);
				break;
		}
	}

}
