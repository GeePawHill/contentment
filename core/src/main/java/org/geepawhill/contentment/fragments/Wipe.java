package org.geepawhill.contentment.fragments;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.Fragment;

public class Wipe implements Fragment
{
	
	public Wipe()
	{
	}

	@Override
	public void prepare(Context context)
	{
		context.canvas.getChildren().clear();
	}

	@Override
	public boolean interpolate(Context context, double fraction)
	{
		return false;
	}

}
