package org.geepawhill.contentment.core;

import org.geepawhill.contentment.tree.KeyValue;
import org.geepawhill.contentment.tree.TreeOutput;

public interface Dumpable
{
	public void dump(TreeOutput<KeyValue> output);
}
