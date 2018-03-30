package org.geepawhill.contentment.fragments;

import org.geepawhill.contentment.core.*;

public class Fader implements Fragment
{
	private GroupSource actor;
	private double from;
	private double to;

	public Fader(GroupSource actor, double from, double to)
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
