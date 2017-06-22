package org.geepawhill.contentment.step;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.OnFinished;
import org.geepawhill.contentment.timing.Timing;

public class SyncStep implements Step, Sync
{
	private long target;
	private Timing timing;

	public SyncStep(long target, long ms)
	{
		this.target = target;
		this.timing = Timing.ms((double)ms);
	}
	
	@Override
	public void slow(Context context, OnFinished onFinished)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void fast(Context context)
	{
	}

	@Override
	public void undo(Context context)
	{
	}

	@Override
	public Timing timing()
	{
		return timing;
	}

	@Override
	public long target()
	{
		return target;
	}

}
