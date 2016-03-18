package org.geepawhill.contentment;

public class TestStep implements Step
{
	
	static public TestStep oneStep = new TestStep();;
	static public TestStep twoStep = new TestStep();;
	static Sequence twoStepSequence = new Sequence(oneStep,twoStep);
	static Sequence oneStepSequence = new Sequence(oneStep);
	
	public boolean isBefore;
	public boolean isPlaying;
	public boolean paused;
	
	public TestStep()
	{
		isBefore=true;
		isPlaying=false;
		paused=false;
	}

	@Override
	public void jumpAfter()
	{
		isBefore=false;
		isPlaying=false;
	}

	@Override
	public void jumpBefore()
	{
		isBefore=true;
		isPlaying=false;
	}
	
	@Override
	public void play()
	{
		isPlaying=true;
	}
	
	@Override
	public void pause()
	{
		paused=true;
	}
	
	
	
	
}