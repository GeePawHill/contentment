package org.geepawhill.contentment.step;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.model.OnFinished;
import org.geepawhill.contentment.model.Step;
import org.geepawhill.contentment.model.Timing;
import org.geepawhill.contentment.timing.FixedTiming;

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
	public void after(Context context)
	{
		group.setVisible(false);
	}

	@Override
	public void before(Context context)
	{
		group.setVisible(true);

	}

	@Override
	public void play(Context context, OnFinished onFinished)
	{
		after(context);
		onFinished.run();
	}
}
