package org.geepawhill.contentment.step;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.OnFinished;
import org.geepawhill.contentment.core.Step;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.geometry.PointPairConsumer;
import org.geepawhill.contentment.timing.FixedTiming;
import org.geepawhill.contentment.timing.Timing;

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
	public Timing timing()
	{
		return FixedTiming.INSTANT;
	}

}
