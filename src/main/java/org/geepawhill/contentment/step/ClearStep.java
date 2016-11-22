package org.geepawhill.contentment.step;

import java.util.ArrayList;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.Step;

import javafx.scene.Node;

public class ClearStep implements Step
{
	
	
	private ArrayList<Node> nodes;

	public ClearStep()
	{
		nodes = new ArrayList<>();
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
	public void play(Context context)
	{
		after(context);
		context.onFinished.run();
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
