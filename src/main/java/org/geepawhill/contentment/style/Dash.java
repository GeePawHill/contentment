package org.geepawhill.contentment.style;

import org.geepawhill.contentment.core.Style;
import org.geepawhill.contentment.core.StyleApplier;

import javafx.scene.shape.Shape;

public class Dash
{

	public static final String KIND = "Dash";

	public static Style solid()
	{
		StyleApplier applier = new StyleApplier() {
			@Override
			public void apply(Shape shape)
			{
				shape.getStrokeDashArray().clear();;
			} 
		};
		return new Style(KIND,"SOLID", applier, "SOLID");		
	}

	public static Style dash(String nickname, Double... dash)
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
		return new Style(KIND,nickname, applier, value);		
	}

}
