package org.geepawhill.contentment.test;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.model.OnFinished;
import org.geepawhill.contentment.model.Step;
import org.geepawhill.contentment.model.Timing;
import org.geepawhill.contentment.timing.FixedTiming;

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
	public void after(Context context)
	{
		isBefore=false;
		isPlaying=false;
		isChanged=true;
	}

	@Override
	public void before(Context context)
	{
		isBefore=true;
		isPlaying=false;
		isChanged=true;
	}
	
	@Override
	public void play(Context context, OnFinished onFinished)
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
