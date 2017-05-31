package org.geepawhill.contentment.test;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.OnFinished;
import org.geepawhill.contentment.step.Step;
import org.geepawhill.contentment.timing.FixedTiming;
import org.geepawhill.contentment.timing.Timing;

public class TestStep implements Step
{
	
	public boolean isBefore;
	public boolean isPlaying;
	public boolean isChanged;
	private OnFinished onFinished;
	
	
	public TestStep()
	{
		isBefore=true;
		isPlaying=false;
		isChanged=false;
	}

	@Override
	public void fast(Context context)
	{
		isBefore=false;
		isPlaying=false;
		isChanged=true;
	}

	@Override
	public void undo(Context context)
	{
		isBefore=true;
		isPlaying=false;
		isChanged=true;
	}
	
	@Override
	public void slow(Context context, OnFinished onFinished)
	{
		this.onFinished = onFinished;
		isBefore=false;
		isPlaying=true;
		isChanged=true;
	}
	
	public void finishPlaying()
	{
		isPlaying=false;
		isBefore=false;
		onFinished.run();
		
	}

	@Override
	public Timing timing()
	{
		return FixedTiming.INSTANT;
	}

}
