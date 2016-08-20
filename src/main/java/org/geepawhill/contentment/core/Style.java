package org.geepawhill.contentment.core;

import javafx.scene.paint.Paint;

public class Style
{

	public final StyleId id;
	public final Object value;

	private Style(StyleId id, Object value)
	{
		this.id = id;
		this.value = value;
	}
	
	static public Style lineColor(Paint paint)
	{
		return new Style(StyleId.LineColor,paint);
	}
	
	static public Style penWidth(double	width)
	{
		return new Style(StyleId.PenWidth,width);
	}
	
	@Override
	public String toString()
	{
		return id.toString()+": "+value.toString();
	}

}
