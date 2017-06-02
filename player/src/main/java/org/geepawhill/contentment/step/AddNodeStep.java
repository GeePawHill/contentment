package org.geepawhill.contentment.step;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.OnFinished;
import org.geepawhill.contentment.timing.Timing;

import javafx.scene.Group;
import javafx.scene.Node;

public class AddNodeStep implements Step
{
	
	private Group group;
	private Node node;

	public AddNodeStep(Group group, Node node)
	{
		this.group = group;
		this.node = node;
	}

	@Override
	public void slow(Context context, OnFinished onFinished)
	{
		fast(context);
		onFinished.run();
	}

	@Override
	public void fast(Context context)
	{
		if(!group.getChildren().contains(node)) group.getChildren().add(node);
	}

	@Override
	public void undo(Context context)
	{
		if(group.getChildren().contains(node)) group.getChildren().remove(node);
	}

	@Override
	public Timing timing()
	{
		return Timing.instant();
	}
	
	@Override
	public String toString()
	{
		String id = node.getId();
		id = node.getClass().getSimpleName();
		return "AddNode: "+id;
	}

}
