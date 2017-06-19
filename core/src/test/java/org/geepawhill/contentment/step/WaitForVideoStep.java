package org.geepawhill.contentment.step;

import org.geepawhill.contentment.core.Animator;
import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.OnFinished;
import org.geepawhill.contentment.step.Step;
import org.geepawhill.contentment.timing.Timing;

public class WaitForVideoStep implements Step
{
	
	private long beat;
	private OnFinished onFinished;

	public WaitForVideoStep(long beat)
	{
		this.beat = beat;
	}

	@Override
	public void slow(Context context, OnFinished onFinished)
	{
		this.onFinished = onFinished;
		new Animator().play(context,OnFinished.NONE,(double)beat*2d,this::updateBeat);
	}
	
	private void updateBeat(Context context, double fraction)
	{
		context.rhythm.update();
		if(context.rhythm.beat()>=beat) onFinished.run();
	}

	@Override
	public void fast(Context context)
	{
		context.rhythm.seekHard(beat);
	}

	@Override
	public void undo(Context context)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public Timing timing()
	{
		return Timing.ms(beat);
	}

}
