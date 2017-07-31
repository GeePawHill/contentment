package org.geepawhill.contentment.atom;

import org.geepawhill.contentment.core.Atom;
import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.utility.JfxUtility;

import javafx.scene.Node;

public class AddAtom implements Atom
{
	private GroupSource actor;
	private Node node;

	public AddAtom(GroupSource actor, Node node)
	{
		this.actor = actor;
		this.node = node;
	}

	@Override
	public void setup(Context context)
	{
		JfxUtility.addIfNeeded(actor, node);
	}

	@Override
	public boolean partial(Context context, double fraction)
	{
		return false;
	}
}
