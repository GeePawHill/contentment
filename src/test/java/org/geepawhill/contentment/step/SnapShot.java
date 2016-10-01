package org.geepawhill.contentment.step;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.geepawhill.contentment.core.Context;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;

public class SnapShot
{
	
	public final Group root;

	public SnapShot(Context context)
	{
		root = context.canvas;
	}
	
	public Node lookup(String target)
	{
		Set<Node> result = lookupAll(target);
		if(result.isEmpty()) return null;
		return result.iterator().next();
	}
	
	public Set<Node> lookupAll(String target)
	{
		return lookupFrom(root,target);
	}

	public Set<Node> lookupFrom(Node node, String target)
	{
		HashSet<Node> result = new HashSet<Node>();
		if(target.equals(node.getId()))
		{
			result.add(node);
		}
		if(node instanceof Parent)
		{
			for(Node child : ((Parent)node).getChildrenUnmodifiable())
			{
				result.addAll(lookupFrom(child,target));
			}
		}
		return result;

	}
	


}
