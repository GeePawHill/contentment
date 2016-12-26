package org.geepawhill.contentment.tree;

import java.util.List;

import javafx.scene.control.TreeItem;

public class Dump implements Tree<KeyValue>
{
	
	private TypedTree<KeyValue> tree;

	public Dump()
	{
		tree = new TypedTree<KeyValue>();
	}
	
	public void append(String key)
	{
		append(new KeyValue(key));
	}
	
	public void append(String key,String value)
	{
		append(new KeyValue(key,value));
	}

	@Override
	public void indent()
	{
		tree.indent();
	}

	@Override
	public void append(KeyValue data)
	{
		tree.append(data);
	}

	@Override
	public void dedent()
	{
		tree.dedent();
	}

	@Override
	public List<Appendee<KeyValue>> asList()
	{
		return tree.asList();
	}

	@Override
	public List<KeyValue> asLeafList()
	{
		return tree.asLeafList();
	}

	@Override
	public String asText(String root)
	{
		return tree.asText(root);
	}

	@Override
	public TreeItem<KeyValue> asTree(TreeItem<KeyValue> root)
	{
		return tree.asTree(root);
	}

}
