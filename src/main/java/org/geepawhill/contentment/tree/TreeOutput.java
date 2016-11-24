package org.geepawhill.contentment.tree;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.TreeItem;

public class TreeOutput<T>
{
	private final ArrayList<Appendee<T>> items;
	private int indent;

	public TreeOutput()
	{
		items = new ArrayList<>();
		indent = 1;
	}

	public void indent()
	{
		if(items.isEmpty() || items.get(items.size()-1).indent!=indent)
		{
			throw new RuntimeException("Two indents with no added item.");
		}
		indent += 1;
	}

	public void append(T data)
	{
		items.add(new Appendee<T>(indent, data));
	}

	public void dedent()
	{
		indent -= 1;
		if (indent == 0) throw new RuntimeException("Too many dedents!");
	}

	public List<Appendee<T>> asList()
	{
		return items;
	}

	public String asText(String root)
	{
		StringBuffer result = new StringBuffer(root + "\n");
		for (Appendee<T> appendee : items)
		{
			for (int tab = 0; tab < appendee.indent; tab++)
				result.append("   ");
			result.append(appendee.data.toString() + "\n");
		}
		return result.toString();
	}

	public TreeItem<T> asTree(TreeItem<T> root)
	{
		int lastIndent=1;
		for (Appendee<T> appendee : items)
		{
			TreeItem<T> newItem = new TreeItem<>(appendee.data);
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

}
