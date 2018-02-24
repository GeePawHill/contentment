package org.geepawhill.contentment.atom;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.Fragment;
import org.geepawhill.contentment.utility.JfxUtility;

public class RemoveAtom implements Fragment
{
	private GroupSource group;
	private NodeSource nodeSource;

	public RemoveAtom(GroupSource group, NodeSource nodeSource)
	{
		this.group = group;
		this.nodeSource = nodeSource;
	}
	
	@Override
	public void prepare(Context context)
	{
	}

	@Override
	public boolean interpolate(Context context, double fraction)
	{
		JfxUtility.removeIfNeeded(group.get(), nodeSource.get());
		return false;
	}
}
