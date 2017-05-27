package org.geepawhill.contentment.step;

import java.util.ArrayList;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.OnFinished;
import org.geepawhill.contentment.timing.FixedTiming;
import org.geepawhill.contentment.timing.Timing;

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
	public void unplay(Context context)
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

}
