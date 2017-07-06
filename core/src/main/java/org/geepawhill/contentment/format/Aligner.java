package org.geepawhill.contentment.format;

import org.geepawhill.contentment.geometry.Point;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;

public class Aligner
{

	private HPos hAlign;
	private VPos vAlign;

	public Aligner(HPos hAlign)
	{
		this(hAlign, VPos.TOP);
	}

	public Aligner(HPos hAlign, VPos vAlign)
	{
		this.hAlign = hAlign;
		this.vAlign = vAlign;
	}

	public static Aligner align(HPos align)
	{
		return new Aligner(align);
	}

	public void align(Point point, Shape... shapes)
	{
		for (Shape shape : shapes)
		{
			halign(point, shape);
			valign(point, shape);
		}

	}

	public void halign(Point point, Shape shape)
	{
		switch (hAlign)
		{
		default:
		case CENTER:
			shape.setTranslateX(point.x - shape.getBoundsInLocal().getWidth() / 2d);
			break;
		case LEFT:
			shape.setTranslateX(point.x);
			break;
		case RIGHT:
			shape.setTranslateX(point.x - shape.getBoundsInLocal().getWidth());
			break;
		}
	}

	public void valign(Point point, Shape shape)
	{
		if(shape instanceof Text)
		{
			((Text)shape).setTextOrigin(VPos.TOP);
		}
		switch (vAlign)
		{
		case CENTER:
			shape.setTranslateY(point.y - shape.getBoundsInLocal().getHeight() / 2d);
			break;
		case TOP:
			shape.setTranslateY(point.y);
			break;
		case BASELINE:
		case BOTTOM:
			shape.setTranslateY(point.y - shape.getBoundsInLocal().getHeight());
			break;
		}
	}

	public static Aligner rightCenter()
	{
		return new Aligner(HPos.RIGHT,VPos.CENTER);
	}

}
