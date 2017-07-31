package org.geepawhill.contentment.atom;

import org.geepawhill.contentment.actor.Actor;
import org.geepawhill.contentment.core.Atom;
import org.geepawhill.contentment.core.Context;

public class EntranceAtom implements Atom
{
	
	private GroupSource actor;

	public EntranceAtom(GroupSource actor)
	{
		this.actor = actor;
	}

	@Override
	public void setup(Context context)
	{
		actor.get().getChildren().clear();
		actor.get().setOpacity(1);
	}

	@Override
	public boolean partial(Context context, double fraction)
	{
		context.add(actor);
		return false;
	}

}
