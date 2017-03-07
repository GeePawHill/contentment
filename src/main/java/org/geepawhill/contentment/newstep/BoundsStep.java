package org.geepawhill.contentment.newstep;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.geometry.PointPairConsumer;
import org.geepawhill.contentment.model.OnFinished;
import org.geepawhill.contentment.model.Step;
import org.geepawhill.contentment.model.Timing;
import org.geepawhill.contentment.timing.FixedTiming;

import javafx.scene.Node;

public class BoundsStep implements Step
{
	
	private Node source;
	private PointPairConsumer onCollected;

	public BoundsStep(Node source,PointPairConsumer onCollected)
	{
		this.source = source;
		this.onCollected = onCollected;
	}

	@Override
	public void after(Context context)
	{
		onCollected.accept(new PointPair(source.getBoundsInParent()));
	}

	@Override
	public void before(Context context)
	{
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
	}

	@Override
	public void resume(Context context)
	{
	}

	@Override
	public Timing timing()
	{
		return FixedTiming.INSTANT;
	}

}
