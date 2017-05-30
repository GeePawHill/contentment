package org.geepawhill.contentment.step;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.OnFinished;
import org.geepawhill.contentment.timing.Timing;

public interface NewStep
{
	public void unplay(Context context);
	public void play(double ms,Context context,OnFinished onFinished);
	public Timing timing();
}
