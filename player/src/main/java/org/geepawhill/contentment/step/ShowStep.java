package org.geepawhill.contentment.step;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.OnFinished;
import org.geepawhill.contentment.core.Step;
import org.geepawhill.contentment.jfx.JfxUtility;
import org.geepawhill.contentment.timing.FixedTiming;
import org.geepawhill.contentment.timing.Timing;

import javafx.scene.Group;

public class ShowStep implements Step
{

	private Group group;

	public ShowStep(Group group)
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
		JfxUtility.addIfNeeded(context, group);
		group.setVisible(true);
	}

	@Override
	public void before(Context context)
	{
		group.setVisible(false);
	}

	@Override
	public void play(Context context, OnFinished onFinished)
	{
		after(context);
		onFinished.run();
	}
}
