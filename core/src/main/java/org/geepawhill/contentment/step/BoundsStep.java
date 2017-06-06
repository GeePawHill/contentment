package org.geepawhill.contentment.step;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.OnFinished;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.geometry.PointPairConsumer;
import org.geepawhill.contentment.timing.Timing;

import javafx.scene.Node;

public class BoundsStep implements Step
{
	
	private Node source;
	private PointPairConsumer onCollected;
	
	public BoundsStep(ShapeStep step,PointPairConsumer onCollected)
	{
		this(step.shape(),onCollected);
	}

	public BoundsStep(Node source,PointPairConsumer onCollected)
	{
		this.source = source;
		this.onCollected = onCollected;
	}

	@Override
	public void fast(Context context)
	{
		onCollected.accept(new PointPair(source.getBoundsInParent()));
	}

	@Override
	public void undo(Context context)
	{
	}

	@Override
	public void slow(Context context, OnFinished onFinished)
	{
		fast(context);
		onFinished.run();
	}

	@Override
	public Timing timing()
	{
		return Timing.instant();
	}
	
	@Override
	public String toString()
	{
		return "BoundsStep";
	}

}
