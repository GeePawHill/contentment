package org.geepawhill.contentment.step;

import org.geepawhill.contentment.core.Gesture;

public interface Addable extends Gesture
{

	Addable add(Gesture gesture);
	void dump();

}