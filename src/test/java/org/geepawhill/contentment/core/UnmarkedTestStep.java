package org.geepawhill.contentment.core;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.UnmarkedStep;

public class UnmarkedTestStep implements UnmarkedStep
{
	
	public boolean isBefore;
	public boolean isPlaying;
	public boolean isPaused;
	public boolean isChanged;
	
	public UnmarkedTestStep()
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
	}

	@Override
	public void before(Context context)
	{
		isBefore=true;
		isPlaying=false;
		isChanged=true;
	}
	
	@Override
	public void play(Context context)
	{
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

	public void finishPlaying(Context context)
	{
		isPlaying=false;
		isBefore=false;
		isPaused=false;
		context.onFinished.handle(null);
		
	}	

}
