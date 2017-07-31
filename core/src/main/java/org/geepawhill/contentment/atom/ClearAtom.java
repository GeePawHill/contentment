package org.geepawhill.contentment.atom;

import org.geepawhill.contentment.core.Atom;
import org.geepawhill.contentment.core.Context;

public class ClearAtom implements Atom
{
	
	public ClearAtom()
	{
	}

	@Override
	public void setup(Context context)
	{
		context.canvas.getChildren().clear();
	}

	@Override
	public boolean partial(Context context, double fraction)
	{
		return false;
	}

}
