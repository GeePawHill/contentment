package org.geepawhill.contentment.step;

import java.util.ArrayList;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.model.OnFinished;
import org.geepawhill.contentment.model.Step;
import org.geepawhill.contentment.model.Timing;
import org.geepawhill.contentment.timing.FixedTiming;

import javafx.scene.Node;

public class ClearStep implements Step
{
	
	
	private ArrayList<Node> nodes;

	public ClearStep()
	{
		nodes = new ArrayList<>();
	}
	
	@Override
	public Timing timing()
	{
		return FixedTiming.INSTANT;
	}

	@Override
	public void after(Context context)
	{
		nodes.addAll(context.canvas.getChildren());
		context.canvas.getChildren().clear();
	}

	@Override
	public void before(Context context)
	{
		for(Node node : nodes)
		{
			context.canvas.getChildren().add(node);
		}
		nodes.clear();
	}

	@Override
	public void play(Context context, OnFinished onFinished)
	{
		after(context);
		onFinished.run();
	}

	@Override
	public void pause(Context context)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void resume(Context context)
	{
		// TODO Auto-generated method stub

	}

}
