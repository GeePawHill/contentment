package org.geepawhill.contentment.atom;

import org.geepawhill.contentment.core.Atom;
import org.geepawhill.contentment.core.Context;

import javafx.scene.Group;

public class EntranceAtom implements Atom, GroupSource
{
	
	private Group group;
	private GroupSource groupSource;

	public EntranceAtom()
	{
		this.group = new Group();
		this.groupSource = () -> group;
	}
	
	@Override
	public void setup(Context context)
	{
		groupSource.get().getChildren().clear();
		groupSource.get().setOpacity(1);
	}

	@Override
	public boolean partial(Context context, double fraction)
	{
		context.add(groupSource);
		return false;
	}

	@Override
	public Group get()
	{
		return groupSource.get();
	}

}
