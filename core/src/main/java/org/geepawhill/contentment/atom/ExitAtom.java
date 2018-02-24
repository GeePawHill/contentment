package org.geepawhill.contentment.atom;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.Fragment;

public class ExitAtom implements Fragment
{
	private GroupSource actor;

	public ExitAtom(GroupSource actor)
	{
		this.actor = actor;
		
	}

	@Override
	public void prepare(Context context)
	{
	}

	@Override
	public boolean interpolate(Context context, double fraction)
	{
		context.remove(actor);
		return false;
	}

}
