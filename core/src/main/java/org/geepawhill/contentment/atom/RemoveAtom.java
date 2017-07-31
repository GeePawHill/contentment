package org.geepawhill.contentment.atom;

import java.util.function.Supplier;

import org.geepawhill.contentment.actor.Actor;
import org.geepawhill.contentment.core.Atom;
import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.utility.JfxUtility;

import javafx.scene.Group;
import javafx.scene.Node;

public class RemoveAtom implements Atom
{
	private GroupSource group;
	private Supplier<Node> nodeSource;

	public RemoveAtom(Actor actor, Node node)
	{
		this(actor.groupSource(), node);
	}

	public RemoveAtom(GroupSource group, Node node)
	{
		this(group,() -> node);
	}
	
	public RemoveAtom(GroupSource group, Supplier<Node> nodeSource)
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
