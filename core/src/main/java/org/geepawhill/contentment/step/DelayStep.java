package org.geepawhill.contentment.step;

import org.geepawhill.contentment.core.Animator;
import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.ContextInterpolator;
import org.geepawhill.contentment.core.OnFinished;
import org.geepawhill.contentment.timing.Timing;

public class DelayStep implements Step
{

	private Timing timing;

	public DelayStep(double ms)
	{
		this.timing = Timing.ms(ms);
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
	public void slow(Context context, OnFinished onFinished)
	{
		if (context.isSkippingDelays() == true)
		{
			onFinished.run();
		}
		else
		{
			new Animator().play(context, onFinished, timing.ms(), ContextInterpolator.NONE);
		}
	}

	@Override
	public Timing timing()
	{
		return timing;
	}

	public void interpolate(Context context, double fraction)
	{
		System.out.println(fraction);
	}

}
