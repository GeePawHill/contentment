package org.geepawhill.contentment.test;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.OnFinished;
import org.geepawhill.contentment.step.Phrase;
import org.geepawhill.contentment.timing.Timing;

public class TestPhrase extends Phrase
{
	
	public boolean isBefore;
	public boolean isPlaying;
	private OnFinished onFinished;
	private Timing timing;
	
	
	public TestPhrase(Timing timing)
	{
		this.timing = timing;
		isBefore=true;
		isPlaying=false;
	}

	@Override
	public void fast(Context context)
	{
		isBefore=false;
		isPlaying=false;
	}

	@Override
	public void slow(Context context, OnFinished onFinished)
	{
		this.onFinished = onFinished;
		isBefore=false;
		isPlaying=true;
	}
	
	public void finishPlaying()
	{
		isPlaying=false;
		isBefore=false;
		onFinished.run();
	}

}
