package org.geepawhill.contentment.fragments;

import org.geepawhill.contentment.core.*;

import javafx.scene.Group;

/**
 * Makes a group and adds it to the destination, making it available via get for later
 * manipulators.
 * 
 * @author GeePaw
 *
 */
public class Entrance implements Fragment, GroupSource
{
	private Group group;

	public Entrance(Group destination, Group group)
	{
		this.group = group;
	}

	@Override
	public void prepare(Context context)
	{
		group.setOpacity(1);
		context.add(this);
	}

	@Override
	public boolean interpolate(Context context, double fraction)
	{
		return false;
	}

	@Override
	public Group group()
	{
		return group;
	}
}
