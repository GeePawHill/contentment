package org.geepawhill.contentment.fragments;

import java.util.function.Supplier;

import javafx.scene.Group;

public interface GroupSource extends Supplier<Group>
{
	public final static GroupSource NONE = new GroupSource()
	{

		@Override
		public Group get()
		{
			throw new RuntimeException("No GroupSource set.");
		}
	};
}
