package org.geepawhill.contentment.step;

import org.geepawhill.contentment.atom.MarkAtom;
import org.geepawhill.contentment.core.AtomRunner;
import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.OnFinished;

public class MarkStep implements Step
{
	
	private long mark;
	private OnFinished onFinished;
	private AtomRunner atomRunner;

	public MarkStep(long mark)
	{
		this.mark = mark*1000;
	}

	@Override
	public void slow(Context context, OnFinished onFinished)
	{
		this.onFinished = onFinished;
		long overPlay = mark - context.beat() + 5000;
		atomRunner = new AtomRunner(overPlay,new MarkAtom(mark,this::hitMark),context,this::hitMark);
		atomRunner.play();
	}
	
	private void hitMark()
	{
		atomRunner.stop();
		onFinished.run();
	}
	
	@Override
	public void fast(Context context)
	{
	}

}
