package org.geepawhill.contentment.step;

import org.geepawhill.contentment.core.Animator;
import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.ContextInterpolator;
import org.geepawhill.contentment.core.OnFinished;
import org.geepawhill.contentment.timing.Timing;

public class UndolessStep implements Step
{
	private ContextInterpolator interpolator;
	private Animator animator;
	private Timing timing;

	public UndolessStep(ContextInterpolator interpolator)
	{
		this.interpolator = interpolator;
		this.animator = new Animator();
		this.timing = Timing.instant();
	}
	
	@Override
	public void fast(Context context)
	{
		animator.play(context, null, 0d, interpolator);
	}

	@Override
	public void undo(Context context)
	{
	}

	@Override
	public void slow(Context context, OnFinished onFinished)
	{
		animator.play(context,onFinished,0d,interpolator);
	}

	@Override
	public Timing timing()
	{
		return timing;
	}

}