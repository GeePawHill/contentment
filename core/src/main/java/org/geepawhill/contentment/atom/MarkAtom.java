package org.geepawhill.contentment.atom;

import org.geepawhill.contentment.core.Atom;
import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.OnFinished;

public class MarkAtom implements Atom
{

	private long ms;
	private OnFinished onPastMark;

	public MarkAtom(long ms, OnFinished onPastMark)
	{
		this.ms = ms;
		this.onPastMark = onPastMark;
	}

	@Override
	public void setup(Context context)
	{
	}

	@Override
	public boolean partial(Context context, double fraction)
	{
		if (context.beat() > ms)
		{
			return false;
		}
		return true;
	}

}
