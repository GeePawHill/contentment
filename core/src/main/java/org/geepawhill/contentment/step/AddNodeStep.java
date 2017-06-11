package org.geepawhill.contentment.step;

import org.geepawhill.contentment.actor.Actor;
import org.geepawhill.contentment.core.Context;

import javafx.scene.Group;
import javafx.scene.Node;

public class AddNodeStep implements Fast
{
	private Group group;
	private Node node;

	public AddNodeStep(Actor actor, ShapeStep step)
	{
		this(actor.group(), step);
	}

	public AddNodeStep(Group group, ShapeStep step)
	{
		this(group, step.shape());
	}

	public AddNodeStep(Actor actor, Node node)
	{
		this(actor.group(), node);
	}

	public AddNodeStep(Group group, Node node)
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
