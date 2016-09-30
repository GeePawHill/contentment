package org.geepawhill.contentment.step;

import org.geepawhill.contentment.core.Context;

import javafx.scene.Group;

public class SnapShot
{
	
	public final Group root;

	public SnapShot(Context context)
	{
		root = context.canvas;
	}

}
