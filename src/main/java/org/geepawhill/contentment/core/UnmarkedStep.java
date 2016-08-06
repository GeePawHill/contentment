package org.geepawhill.contentment.core;

public interface UnmarkedStep
{
	public void after(Context context);
	public void before(Context context);
	public void play(Context context);
	public void pause(Context context);
	public void resume(Context context);
}
