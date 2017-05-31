package org.geepawhill.contentment.step;

import org.geepawhill.contentment.actor.Actor;
import org.geepawhill.contentment.actor.Actors;
import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.OnFinished;
import org.geepawhill.contentment.timing.FixedTiming;
import org.geepawhill.contentment.timing.Timing;

import javafx.scene.Node;

public class ClearStep implements Step
{
	
	private Actors actors = new Actors();

	public ClearStep()
	{
		actors = new Actors();
	}
	
	@Override
	public Timing timing()
	{
		return FixedTiming.INSTANT;
	}

	@Override
	public void fast(Context context)
	{
		actors.clear();
		actors.addAll(context.actors);
		System.out.println("Clearing:");
		for(Actor actor : actors)
		{
			context.canvas.getChildren().remove(actor.group());
		}
		context.canvas.getChildren().clear();
	}

	@Override
	public void undo(Context context)
	{
		for(Actor actor : actors)
		{
			context.canvas.getChildren().add(actor.group());
		}
		for(Node node : actors.get(0).group().getChildren())
		{
			System.out.println(node.isVisible());
		}
		actors.clear();
	}

	@Override
	public void slow(Context context, OnFinished onFinished)
	{
		fast(context);
		onFinished.run();
	}
	
	@Override
	public String toString()
	{
		return "Clear";
	}

}
