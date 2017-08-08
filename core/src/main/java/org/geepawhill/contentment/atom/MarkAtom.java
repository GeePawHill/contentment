package org.geepawhill.contentment.atom;

import org.geepawhill.contentment.core.Atom;
import org.geepawhill.contentment.core.Context;

public class MarkAtom implements Atom
{

	private long mark;

	public MarkAtom(long mark)
	{
		this.mark = mark;
	}

	@Override
	public void setup(Context context)
	{
	}

	@Override
	public boolean partial(Context context, double fraction)
	{
		if (context.beat() > mark)
		{
			System.out.println("Beat: "+context.beat()+" Mark: "+mark);
			return false;
		}
		return true;
	}

}
