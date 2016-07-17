package org.geepawhill.contentment;

import java.util.ArrayList;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

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

	public void removeFrom(Pane canvas)
	{
		for (Node node : nodes)
		{
			canvas.getChildren().remove(node);
		}
	}

	public void addTo(Pane canvas)
	{
		for (Node node : nodes)
		{
			canvas.getChildren().add(node);
		}
	}

}
