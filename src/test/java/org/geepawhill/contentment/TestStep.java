package org.geepawhill.contentment;

public class TestStep implements Step
{
	
	static public TestStep oneStep = new TestStep();;
	static public TestStep twoStep = new TestStep();;
	static Sequence twoStepSequence = new Sequence(oneStep,twoStep);
	static Sequence oneStepSequence = new Sequence(oneStep);
	
	public boolean isBefore;
	public boolean isPlaying;
	public boolean isPaused;
	
	public TestStep()
	{
		isBefore=true;
		isPlaying=false;
		isPaused=false;
	}

	@Override
	public void after(Context context)
	{
		isBefore=false;
		isPlaying=false;
	}

	@Override
	public void before(Context context)
	{
		isBefore=true;
		isPlaying=false;
	}
	
	@Override
	public void play(Context context)
	{
		isPlaying=true;
	}
	
	@Override
	public void pause(Context context)
	{
		isPaused=true;
	}

	@Override
	public void resume(Context context) {
		isPaused=false;
	}	
}