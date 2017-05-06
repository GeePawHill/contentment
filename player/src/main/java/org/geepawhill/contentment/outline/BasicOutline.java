package org.geepawhill.contentment.outline;

import java.util.ArrayList;
import java.util.List;

import org.geepawhill.contentment.model.Outline;

import javafx.scene.control.TreeItem;

public class BasicOutline<T> implements Outline<T>
{
	private final ArrayList<Line<T>> items;
	private int indent;

	public BasicOutline()
	{
		items = new ArrayList<>();
		indent = 1;
	}

	@Override
	public void indent()
	{
		if(items.isEmpty() || items.get(items.size()-1).indent!=indent)
		{
			throw new RuntimeException("Two indents with no added item.");
		}
		indent += 1;
	}

	@Override
	public void append(T data)
	{
		items.add(new Line<T>(indent, data));
	}

	@Override
	public void dedent()
	{
		indent -= 1;
		if (indent == 0) throw new RuntimeException("Too many dedents!");
	}

	@Override
	public List<Line<T>> asList()
	{
		return items;
	}
	
	@Override
	public List<T> asLeafList()
	{
		ArrayList<T> result = new ArrayList<>();
		TreeItem<T> root = new TreeItem<T>();
		asTree(root);
		addLeafs(result,root);
		return result;
	}
	
	private void addLeafs(List<T> result,TreeItem<T> item)
	{
		if(item.getChildren().isEmpty())
		{
			result.add(item.getValue());
		}
		for(TreeItem<T> child : item.getChildren())
		{
			addLeafs(result,child);
		}
	}

	@Override
	public String asText(String root)
	{
		StringBuffer result = new StringBuffer(root + "\n");
		for (Line<T> appendee : items)
		{
			for (int tab = 0; tab < appendee.indent; tab++)
				result.append("   ");
			result.append(appendee.data.toString() + "\n");
		}
		return result.toString();
	}

	@Override
	public TreeItem<T> asTree(TreeItem<T> root)
	{
		root.setExpanded(true);
		int lastIndent=1;
		for (Line<T> appendee : items)
		{
			TreeItem<T> newItem = new TreeItem<>(appendee.data);
			newItem.setExpanded(true);
			for(int i=0;i<(lastIndent-appendee.indent);i++)
			{
				root = root.getParent();
			}
			if(appendee.indent>lastIndent)
			{
				root = root.getChildren().get(root.getChildren().size()-1);
			}
			root.getChildren().add(newItem);
			lastIndent = appendee.indent;
		}
		return root;
	}

	public void clear()
	{
		items.clear();
	}

}
