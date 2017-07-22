package org.geepawhill.contentment.atom;

import org.geepawhill.contentment.actor.Actor;
import org.geepawhill.contentment.core.Atom;
import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.utility.JfxUtility;

import javafx.scene.Node;

public class AddAtom implements Atom
{
	private Actor actor;
	private Node node;

	public AddAtom(Actor actor, Node node)
	{
		this.actor = actor;
		this.node = node;
	}

	@Override
	public void setup(Context context)
	{
	}

	@Override
	public boolean partial(Context context, double fraction)
	{
		JfxUtility.addIfNeeded(actor, node);
		return false;
	}
}
