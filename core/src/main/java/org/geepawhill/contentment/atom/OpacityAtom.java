package org.geepawhill.contentment.atom;

import org.geepawhill.contentment.actor.Actor;
import org.geepawhill.contentment.core.Atom;
import org.geepawhill.contentment.core.Context;

public class OpacityAtom implements Atom
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
	public void setup(Context context)
	{
		actor.get().setOpacity(from);
	}

	@Override
	public boolean partial(Context context, double fraction)
	{
		actor.get().setOpacity(from + fraction * (to - from));
		return true;
	}

}
