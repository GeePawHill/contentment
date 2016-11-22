package org.geepawhill.contentment.core;

public interface Step
{
	public void after(Context context);
	public void before(Context context);
	public void play(Context context, OnFinished onFinished);
	public void pause(Context context);
	public void resume(Context context);
	public Timing timing();
}
