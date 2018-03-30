package org.geepawhill.contentment.fragments;

import org.geepawhill.contentment.core.*;

public class Exit implements Fragment
{
	private GroupSource actor;

	public Exit(GroupSource actor)
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
