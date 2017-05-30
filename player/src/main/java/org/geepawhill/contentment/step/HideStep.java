package org.geepawhill.contentment.step;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.OnFinished;
import org.geepawhill.contentment.timing.FixedTiming;
import org.geepawhill.contentment.timing.Timing;

import javafx.scene.Group;

public class HideStep implements Step
{
	
	private Group group;

	public HideStep(Group group)
	{
		this.group = group;
	}
	
	@Override
	public Timing timing()
	{
		return FixedTiming.INSTANT;
	}

	@Override
	public void instant(Context context)
	{
		group.setVisible(false);
	}

	@Override
	public void undo(Context context)
	{
		group.setVisible(true);

	}

	@Override
	public void slow(Context context, OnFinished onFinished)
	{
		instant(context);
		onFinished.run();
	}
}
