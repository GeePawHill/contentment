package org.geepawhill.contentment.step;

import org.geepawhill.contentment.core.Animator;
import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.OnFinished;

public class MarkStep implements Step
{
	
	private long mark;
	private Animator animator;
	private OnFinished onFinished;

	public MarkStep(long mark)
	{
		this.mark = mark*1000;
		this.animator = new Animator();
	}

	@Override
	public void slow(Context context, OnFinished onFinished)
	{
		this.onFinished = onFinished;
		animator.play(context,onFinished,(double)(mark-context.beat())+5000d,this::interpolate);

	}
	
	public void interpolate(Context context, double fraction)
	{
		if(context.beat()>=mark)
		{
			animator.stop();
			onFinished.run();
		}
	}

	@Override
	public void fast(Context context)
	{
	}

}
