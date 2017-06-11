package org.geepawhill.contentment.step;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.geometry.PointPairConsumer;

import javafx.scene.Node;

public class BoundsStep implements Fast
{

	private Node source;
	private PointPairConsumer onCollected;

	public BoundsStep(ShapeStep step, PointPairConsumer onCollected)
	{
		this(step.shape(), onCollected);
	}

	public BoundsStep(Node source, PointPairConsumer onCollected)
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
	public String toString()
	{
		return "BoundsStep";
	}

}
