package org.geepawhill.contentment.style;

import org.geepawhill.contentment.tree.KeyValue;
import org.geepawhill.contentment.tree.TreeOutput;

import javafx.scene.shape.Shape;

public class Style
{

	@FunctionalInterface
	public static interface ShapeApplier
	{
		public void apply(Shape shape);
	}

	public final StyleId id;
	private final ShapeApplier applier;
	private String nickname;

	public Style(String nickname, StyleId id, ShapeApplier applier)
	{
		this.nickname = nickname;
		this.id = id;
		this.applier = applier;
	}

	@Override
	public String toString()
	{
		return nickname;
	}

	public void apply(Shape shape)
	{
		applier.apply(shape);
	}
	
	public void dump(TreeOutput<KeyValue> output)
	{
		output.append(new KeyValue(id.toString(),nickname));
	}
}
