package org.geepawhill.contentment.tree;

import javafx.scene.control.TreeItem;

public class KeyValueItem extends TreeItem<KeyValue>
{
	public KeyValueItem(String key, String value)
	{
		super(new KeyValue(key, value));
	}

	public KeyValueItem(String key)
	{
		this(key, "");
	}

	public String key()
	{
		return getValue().getKey();
	}

	public String fullKey()
	{
		StringBuilder result = new StringBuilder(key());
		KeyValueItem parent = (KeyValueItem) getParent();
		while (parent != null)
		{
			result.insert(0, parent.key() + ".");
			parent = (KeyValueItem) parent.getParent();
		}
		return result.toString();
	}

	public String value()
	{
		return getValue().getValue();
	}
	
	public boolean keysMatch(KeyValueItem right)
	{
		return key().equals(right.key());
	}
	
	public boolean valuesMatch(KeyValueItem right)
	{
		return value().equals(right.value());
	}
	
}