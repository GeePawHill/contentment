package org.geepawhill.contentment.jfx;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.model.Actor;

import javafx.scene.Group;
import javafx.scene.Node;

public class JfxUtility
{
	static public void addIfNeeded(Context context, Group group)
	{
		if(!context.canvas.getChildren().contains(group))
		{
			context.canvas.getChildren().add(group);
		}
	}
	
	static public void setId(int index,Actor actor,Node node)
	{
		node.setId(actor.getClass().getSimpleName()+String.format("%1$02d",index)+"."+node.getClass().getSimpleName());
	}

	public static Group makeGroup(int index, Actor actor, Node... nodes)
	{
		Group group = new Group();
		for(Node node : nodes)
		{
			group.getChildren().add(node);
			setId(index,actor,node);
		}
		return group;
	}

	public static void removeIfNeeded(Context context, Group group)
	{
		if(context.canvas.getChildren().contains(group))
		{
			context.canvas.getChildren().remove(group);
		}
	}

}
