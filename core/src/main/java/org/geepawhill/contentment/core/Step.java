package org.geepawhill.contentment.core;

import org.geepawhill.contentment.timing.Timing;

public interface Step
{
	public void after(Context context);
	public void before(Context context);
	public void play(Context context, OnFinished onFinished);
	public Timing timing();
}
