package org.geepawhill.contentment.step;

import org.geepawhill.contentment.core.Animator;
import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.OnFinished;
import org.geepawhill.contentment.step.Step;
import org.geepawhill.contentment.timing.Timing;

public class WaitForVideoStep implements Step, CueMarker
{

	private long beat;
	private OnFinished onFinished;
	private Animator animator;

	public WaitForVideoStep(long beat)
	{
		this.beat = beat;
	}

	@Override
	public void slow(Context context, OnFinished onFinished)
	{
		this.onFinished = onFinished;
		this.animator = new Animator();
		animator.play(context, OnFinished.NONE, (double) beat * 2d, this::updateBeat);
	}
	
	private void finishAndDie()
	{
		animator.stop();
		onFinished.run();
	}

	private void updateBeat(Context context, double fraction)
	{
		context.getRhythm().update();
		if (context.getRhythm().beat() >= beat)
		{
			finishAndDie();
		}
	}

	@Override
	public void fast(Context context)
	{
		context.getRhythm().seekHard(beat);
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
