package org.geepawhill.contentment.atom;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.Fragment;

public class OpacityAtom implements Fragment
{
	private GroupSource actor;
	private double from;
	private double to;

	public OpacityAtom(GroupSource actor, double from, double to)
	{
		this.actor = actor;
		this.from = from;
		this.to = to;
	}

	@Override
	public void prepare(Context context)
	{
		actor.get().setOpacity(from);
	}

	@Override
	public boolean interpolate(Context context, double fraction)
	{
		actor.get().setOpacity(from + fraction * (to - from));
		return true;
	}

}
