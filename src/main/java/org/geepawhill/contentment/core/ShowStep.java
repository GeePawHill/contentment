package org.geepawhill.contentment.core;

import javafx.scene.Group;

public class ShowStep implements Step
{

	private Group group;

	public ShowStep(Group group)
	{
		this.group = group;
	}

	@Override
	public void after(Context context)
	{
		if(!context.canvas.getChildren().contains(group)) context.canvas.getChildren().add(group);
		group.setVisible(true);
	}

	@Override
	public void before(Context context)
	{
		group.setVisible(false);
	}

	@Override
	public void play(Context context)
	{
		after(context);
		context.onFinished.handle(null);
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
