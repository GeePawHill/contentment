package org.geepawhill.contentment.step;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.OnFinished;
import org.geepawhill.contentment.fast.Fast;
import org.geepawhill.contentment.timing.Timing;

public class FastStep implements Step
{
	private Fast fast;

	public FastStep(Fast fast)
	{
		this.fast = fast;
	}

	@Override
	public void slow(Context context, OnFinished onFinished)
	{
		fast(context);
		onFinished.run();
	}

	@Override
	public void fast(Context context)
	{
		fast.fast(context);
	}

	@Override
	public void undo(Context context)
	{
		fast.undo(context);
	}

	@Override
	public Timing timing()
	{
		return Timing.instant();
	}
	
	@Override
	public String toString()
	{
		return fast+" (fast)";
	}

}
