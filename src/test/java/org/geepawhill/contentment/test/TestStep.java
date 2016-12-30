package org.geepawhill.contentment.test;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.OnFinished;
import org.geepawhill.contentment.core.Step;
import org.geepawhill.contentment.timing.FixedTiming;
import org.geepawhill.contentment.timing.Timing;

public class TestStep implements Step
{
	
	public boolean isBefore;
	public boolean isPlaying;
	public boolean isPaused;
	public boolean isChanged;
	private OnFinished onFinished;
	
	
	public TestStep()
	{
		isBefore=true;
		isPlaying=false;
		isPaused=false;
		isChanged=false;
	}

	@Override
	public void after(Context context)
	{
		isBefore=false;
		isPlaying=false;
		isChanged=true;
		isPaused=false;
	}

	@Override
	public void before(Context context)
	{
		isBefore=true;
		isPlaying=false;
		isChanged=true;
		isPaused=false;
	}
	
	@Override
	public void play(Context context, OnFinished onFinished)
	{
		this.onFinished = onFinished;
		isBefore=false;
		isPlaying=true;
		isChanged=true;
	}
	
	@Override
	public void pause(Context context)
	{
		isChanged=true;
		isPaused=true;
	}

	@Override
	public void resume(Context context) {
		isPaused=false;
		isChanged=true;
	}

	public void finishPlaying()
	{
		isPlaying=false;
		isBefore=false;
		isPaused=false;
		onFinished.run();
		
	}

	@Override
	public Timing timing()
	{
		return FixedTiming.INSTANT;
	}

}
