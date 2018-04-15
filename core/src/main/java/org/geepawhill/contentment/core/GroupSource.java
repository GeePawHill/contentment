package org.geepawhill.contentment.core;

import javafx.scene.Group;

public interface GroupSource 
{
	public Group group();

	public static GroupSource value(Group group)
	{
		return () -> group;
	}
	
	public static GroupSource VALUE(Group group)
	{
		return ()->group;
	}
	
	
	public final static GroupSource NONE = new GroupSource()
	{

		@Override
		public Group group()
		{
			throw new NoGroupSource();
		}
	};
	
	public static class NoGroupSource extends RuntimeException
	{
		private static final long serialVersionUID = 1L;

		public NoGroupSource()
		{
			super("GroupSource has no group.");
		}
	}

}
