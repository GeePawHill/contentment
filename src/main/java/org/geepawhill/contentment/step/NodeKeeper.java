package org.geepawhill.contentment.step;

import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.Node;

public class NodeKeeper
{
	ArrayList<Node> nodes;

	public NodeKeeper()
	{
		nodes = new ArrayList<Node>();
	}

	public void keep(Node... nodeArgs)
	{
		for (Node node : nodeArgs)
		{
			nodes.add(node);
		}
	}

	public int size()
	{
		return nodes.size();
	}

	public void removeFrom(Group canvas)
	{
		for (Node node : nodes)
		{
			canvas.getChildren().remove(node);
		}
	}

	public void addTo(Group canvas)
	{
		for (Node node : nodes)
		{
			if (!canvas.getChildren().contains(node))
			{
				canvas.getChildren().add(node);
			}
		}
	}

}
