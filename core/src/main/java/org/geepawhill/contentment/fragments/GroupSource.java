package org.geepawhill.contentment.fragments;

import java.util.function.Supplier;

import javafx.scene.Group;

public interface GroupSource extends Supplier<Group>
{
	public static GroupSource VALUE(Group group)
	{
		return ()->group;
	}
	
	public final static GroupSource NONE = new GroupSource()
	{

		@Override
		public Group get()
		{
			throw new NoGroupSource();
		}
	};
	
	public static class NoGroupSource extends RuntimeException
	{
		public NoGroupSource()
		{
			super("GroupSource has no group.");
		}
	}
}
