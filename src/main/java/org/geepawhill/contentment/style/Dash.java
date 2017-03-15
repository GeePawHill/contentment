package org.geepawhill.contentment.style;

import org.geepawhill.contentment.core.Style;
import org.geepawhill.contentment.core.StyleApplier;
import org.geepawhill.contentment.core.StyleId;

import javafx.scene.shape.Shape;

public class Dash
{

	public static Style solid()
	{
		StyleApplier applier = new StyleApplier() {
			@Override
			public void apply(Shape shape)
			{
				shape.getStrokeDashArray().clear();;
			} 
		};
		return new Style("SOLID",StyleId.Dash, applier, "SOLID");		
	}

	public static Style dash(Double... dash)
	{
		StyleApplier applier = new StyleApplier() {
			@Override
			public void apply(Shape shape)
			{
				shape.getStrokeDashArray().clear();
				shape.getStrokeDashArray().addAll(dash);
			} 
		};
		String value = "";
		boolean isFirst = true;
		for(Double dashItem : dash)
		{
			if(!isFirst) value+=", ";
			value+=dashItem;
			isFirst=false;
		}
		return new Style(value,StyleId.Dash, applier, value);		
	}

}
