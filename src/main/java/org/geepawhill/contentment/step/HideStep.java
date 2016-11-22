package org.geepawhill.contentment.step;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.Step;

import javafx.scene.Group;

public class HideStep implements Step
{
	
	private Group group;

	public HideStep(Group group)
	{
		this.group = group;
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
	public void play(Context context)
	{
		after(context);
		context.onFinished.run();
	}

	@Override
	public void pause(Context context)
	{
	}

	@Override
	public void resume(Context context)
	{
	}

}
