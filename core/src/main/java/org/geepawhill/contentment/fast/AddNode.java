package org.geepawhill.contentment.fast;

import org.geepawhill.contentment.actor.Actor;
import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.step.ShapeStep;

import javafx.scene.Group;
import javafx.scene.Node;

public class AddNode implements Fast
{
	private Group group;
	private Node node;

	public AddNode(Actor actor, ShapeStep step)
	{
		this(actor.group(), step);
	}

	public AddNode(Group group, ShapeStep step)
	{
		this(group, step.shape());
	}

	public AddNode(Actor actor, Node node)
	{
		this(actor.group(), node);
	}

	public AddNode(Group group, Node node)
	{
		this.group = group;
		this.node = node;
	}

	@Override
	public void fast(Context context)
	{
		group.getChildren().add(node);
	}

	@Override
	public void undo(Context context)
	{
		group.getChildren().remove(node);
	}

	@Override
	public String toString()
	{
		String id = node.getId();
		id = node.getClass().getSimpleName();
		return "AddNode: " + id;
	}

}
