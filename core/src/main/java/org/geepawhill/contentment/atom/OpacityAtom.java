package org.geepawhill.contentment.atom;

import org.geepawhill.contentment.actor.Actor;
import org.geepawhill.contentment.core.Atom;
import org.geepawhill.contentment.core.Context;

public class OpacityAtom implements Atom
{
	private Actor actor;
	private double from;
	private double to;

	public OpacityAtom(Actor actor, double from, double to)
	{
		this.actor = actor;
		this.from = from;
		this.to = to;
	}

	@Override
	public void setup(Context context)
	{
		actor.group().setOpacity(from);
	}

	@Override
	public boolean partial(Context context, double fraction)
	{
		actor.group().setOpacity(from + fraction * (to - from));
		return true;
	}

}
