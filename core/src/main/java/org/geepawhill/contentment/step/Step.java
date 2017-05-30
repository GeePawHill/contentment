package org.geepawhill.contentment.step;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.OnFinished;
import org.geepawhill.contentment.timing.Timing;

public interface Step
{
	public void instant(Context context);
	public void undo(Context context);
	public void slow(Context context, OnFinished onFinished);
	public Timing timing();
}
