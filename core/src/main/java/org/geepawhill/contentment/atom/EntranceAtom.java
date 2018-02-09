package org.geepawhill.contentment.atom;

import org.geepawhill.contentment.core.Fragment;
import org.geepawhill.contentment.core.Context;

import javafx.scene.Group;

public class EntranceAtom implements Fragment, GroupSource
{
	
	private Group group;

	public EntranceAtom()
	{
		this.group = new Group();
	}
	
	@Override
	public void prepare(Context context)
	{
		group.getChildren().clear();
		group.setOpacity(1);
		context.add(() -> group);
	}

	@Override
	public boolean interpolate(Context context, double fraction)
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
