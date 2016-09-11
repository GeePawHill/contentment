package org.geepawhill.contentment.jfx;

import org.geepawhill.contentment.core.Context;

import javafx.scene.Group;

public class JfxUtility
{
	static public void addIfNeeded(Context context, Group group)
	{
		if(!context.canvas.getChildren().contains(group))
		{
			context.canvas.getChildren().add(group);
		}
	}
}
