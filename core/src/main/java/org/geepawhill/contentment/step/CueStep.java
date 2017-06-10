package org.geepawhill.contentment.step;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.OnFinished;
import org.geepawhill.contentment.timing.Timing;

public class CueStep implements Step
{

	@Override
	public void fast(Context context)
	{
	}

	@Override
	public void undo(Context context)
	{
	}

	@Override
	public void slow(Context context, OnFinished onFinished)
	{
		onFinished.run();
	}

	@Override
	public Timing timing()
	{
		return Timing.instant();
	}

	@Override
	public String toString()
	{
		return "Cue";
	}

}
