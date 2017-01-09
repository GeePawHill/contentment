package org.geepawhill.contentment.core;

import javafx.scene.shape.Shape;

@FunctionalInterface
public interface StyleApplier
{
	public void apply(Shape shape);
}