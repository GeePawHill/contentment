package org.geepawhill.contentment.model;

import org.geepawhill.contentment.core.Context;

public interface Step
{
	public void after(Context context);
	public void before(Context context);
	public void play(Context context, OnFinished onFinished);
	public Timing timing();
}
