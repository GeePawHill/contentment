package org.geepawhill.contentment.style;

import org.geepawhill.contentment.format.Style;
import org.geepawhill.contentment.format.StyleApplier;

import javafx.scene.shape.Shape;

public class Dash
{

	public static final String KEY = "Dash";

	public static Style solid()
	{
		StyleApplier applier = new StyleApplier() {
			@Override
			public void apply(Shape shape)
			{
				shape.getStrokeDashArray().clear();;
			} 
		};
		return new Style(KEY,"SOLID", applier, "SOLID");		
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
		return new Style(KEY,nickname, applier, value);		
	}

}
