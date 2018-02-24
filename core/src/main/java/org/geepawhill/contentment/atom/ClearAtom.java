package org.geepawhill.contentment.atom;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.Fragment;

public class ClearAtom implements Fragment
{
	
	public ClearAtom()
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
