package org.geepawhill.contentment.core;

import org.geepawhill.contentment.tree.KeyValue;
import org.geepawhill.contentment.tree.TypedTree;

public interface Dumpable
{
	public void dump(TypedTree<KeyValue> output);
}
