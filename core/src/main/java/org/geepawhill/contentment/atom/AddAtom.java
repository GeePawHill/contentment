package org.geepawhill.contentment.atom;

import org.geepawhill.contentment.core.Fragment;
import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.utility.JfxUtility;

import javafx.scene.Node;

public class AddAtom implements Fragment
{
	private GroupSource actor;
	private Node node;

	public AddAtom(GroupSource actor, Node node)
	{
		this.actor = actor;
		this.node = node;
	}

	@Override
	public void prepare(Context context)
	{
		JfxUtility.addIfNeeded(actor, node);
	}

	@Override
	public boolean interpolate(Context context, double fraction)
	{
		return false;
	}
}
