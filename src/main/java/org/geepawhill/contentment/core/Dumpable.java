package org.geepawhill.contentment.core;

import org.geepawhill.contentment.outline.BasicOutline;
import org.geepawhill.contentment.outline.KeyValue;

public interface Dumpable
{
	public void dump(BasicOutline<KeyValue> output);
}
