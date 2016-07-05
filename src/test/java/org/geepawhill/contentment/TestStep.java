package org.geepawhill.contentment;

import javafx.scene.layout.Pane;

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
	public void jumpAfter(Pane canvas)
	{
		isBefore=false;
		isPlaying=false;
	}

	@Override
	public void jumpBefore(Pane canvas)
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
		isPaused=true;
	}

	@Override
	public void resume() {
		isPaused=false;
	}

	@Override
	public void stop() {
		isPlaying=false;
		isBefore = true;
	}
	
	
	
	
}