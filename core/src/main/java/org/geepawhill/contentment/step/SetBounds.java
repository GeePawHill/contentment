package org.geepawhill.contentment.step;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.geometry.PointPairConsumer;

import javafx.scene.Node;

public class SetBounds implements Fast
{

	private Node source;
	private PointPairConsumer onCollected;

	public SetBounds(ShapeStep step, PointPairConsumer onCollected)
	{
		this(step.shape(), onCollected);
	}

	public SetBounds(Node source, PointPairConsumer onCollected)
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
