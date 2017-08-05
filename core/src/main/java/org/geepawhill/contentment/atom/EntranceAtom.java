package org.geepawhill.contentment.atom;

import org.geepawhill.contentment.core.Atom;
import org.geepawhill.contentment.core.Context;

import javafx.scene.Group;

public class EntranceAtom implements Atom, GroupSource
{
	
	private Group group;

	public EntranceAtom()
	{
		this.group = new Group();
	}
	
	@Override
	public void setup(Context context)
	{
		group.getChildren().clear();
		group.setOpacity(1);
		context.add(() -> group);
	}

	@Override
	public boolean partial(Context context, double fraction)
	{
		return false;
	}

	@Override
	public Group get()
	{
		return group;
	}
	
	@Override
	public String toString()
	{
		return "Entrance: Group ("+group+")";
	}

}
