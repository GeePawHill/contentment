package org.geepawhill.contentment.step;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.OnFinished;
import org.geepawhill.contentment.timing.Timing;
import org.geepawhill.contentment.utility.JfxUtility;

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
		return Timing.instant();
	}

	@Override
	public void fast(Context context)
	{
		JfxUtility.addIfNeeded(context, group);
		group.setVisible(true);
	}

	@Override
	public void undo(Context context)
	{
		group.setVisible(false);
	}

	@Override
	public void slow(Context context, OnFinished onFinished)
	{
		fast(context);
		onFinished.run();
	}
}
