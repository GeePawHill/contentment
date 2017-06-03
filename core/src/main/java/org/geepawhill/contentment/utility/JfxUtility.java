package org.geepawhill.contentment.utility;

import org.geepawhill.contentment.core.Context;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

public class JfxUtility
{
	static public void addIfNeeded(Context context, Group group)
	{
		if (!context.canvas.getChildren().contains(group))
		{
			context.canvas.getChildren().add(group);
		}
	}

	public static void removeIfNeeded(Context context, Group group)
	{
		if (context.canvas.getChildren().contains(group))
		{
			context.canvas.getChildren().remove(group);
		}
	}

	public static void anchor(Node node)
	{
		AnchorPane.setTopAnchor(node, 0d);
		AnchorPane.setBottomAnchor(node, 0d);
		AnchorPane.setLeftAnchor(node, 0d);
		AnchorPane.setRightAnchor(node, 0d);
	}

	public static AnchorPane makeAnchorFor(Node node)
	{
		AnchorPane anchor = new AnchorPane();
		anchor(node);
		anchor.getChildren().add(node);
		return anchor;
	}

}
