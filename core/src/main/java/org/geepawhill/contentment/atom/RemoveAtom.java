package org.geepawhill.contentment.atom;

import org.geepawhill.contentment.core.Atom;
import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.utility.JfxUtility;

public class RemoveAtom implements Atom
{
	private GroupSource group;
	private NodeSource nodeSource;

	public RemoveAtom(GroupSource group, NodeSource nodeSource)
	{
		this.group = group;
		this.nodeSource = nodeSource;
	}
	
	@Override
	public void setup(Context context)
	{
	}

	@Override
	public boolean partial(Context context, double fraction)
	{
		JfxUtility.removeIfNeeded(group.get(), nodeSource.get());
		return false;
	}
}
