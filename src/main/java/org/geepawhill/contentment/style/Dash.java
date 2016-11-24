package org.geepawhill.contentment.style;

import javafx.scene.shape.Shape;

public class Dash
{

	public static Style solid()
	{
		Style.ShapeApplier applier = new Style.ShapeApplier() {
			@Override
			public void apply(Shape shape)
			{
				shape.getStrokeDashArray().clear();;
			} 
		};
		return new Style("SOLID",StyleId.Dash, applier);		
	}

	public static Style dash(Double... dash)
	{
		Style.ShapeApplier applier = new Style.ShapeApplier() {
			@Override
			public void apply(Shape shape)
			{
				shape.getStrokeDashArray().clear();
				shape.getStrokeDashArray().addAll(dash);
			} 
		};
		String nickname = "";
		boolean isFirst = true;
		for(Double dashItem : dash)
		{
			if(!isFirst) nickname+=", ";
			nickname+=dashItem;
			isFirst=false;
		}
		return new Style(nickname,StyleId.Dash, applier);		
	}

}
