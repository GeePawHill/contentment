package org.geepawhill.contentment.perform;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.OnFinished;

public interface Playable
{

	void undo(Context context);

	void fast(Context context);

	void slow(Context context, OnFinished onFinished);

	long ms();

}