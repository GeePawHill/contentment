package org.geepawhill.contentment.fragments;

import org.geepawhill.contentment.core.*;

/**
 * Changes the opacity of a given Group target.
 * 
 * @author GeePaw
 *
 */
public class Fader implements Fragment
{
	private GroupSource target;
	private double from;
	private double to;

	public Fader(GroupSource target, double from, double to)
	{
		this.target = target;
		this.from = from;
		this.to = to;
	}

	@Override
	public void prepare(Context context)
	{
		target.get().setOpacity(from);
	}

	@Override
	public boolean interpolate(Context context, double fraction)
	{
		target.get().setOpacity(from + fraction * (to - from));
		return true;
	}
}
