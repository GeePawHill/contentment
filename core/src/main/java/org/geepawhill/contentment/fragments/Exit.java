package org.geepawhill.contentment.fragments;

import org.geepawhill.contentment.core.*;

/**
 * Removes a group from the canvas without otherwise altering it. No-ops if
 * the group wasn't on the canvas. Throws if the group was null.
 * 
 * @author GeePaw
 *
 */
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
