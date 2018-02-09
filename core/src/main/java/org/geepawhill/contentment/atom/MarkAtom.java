package org.geepawhill.contentment.atom;

import org.geepawhill.contentment.core.Fragment;
import org.geepawhill.contentment.core.Context;

public class MarkAtom implements Fragment
{

	private long mark;

	public MarkAtom(long mark)
	{
		this.mark = mark;
	}

	@Override
	public void prepare(Context context)
	{
	}

	@Override
	public boolean interpolate(Context context, double fraction)
	{
		if (context.beat() > mark)
		{
			return false;
		}
		return true;
	}

}
