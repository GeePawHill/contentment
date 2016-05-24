package org.geepawhill.contentment;

public class Context {
	public final PlayerState playing;
	public final PausedState paused;
	public final StoppedState stopped;
	
	public Stepper stepper;
	
	public Context()
	{
		playing = new PlayingState();
		paused = new PausedState();
		stopped = new StoppedState();
		stepper = new Stepper();
	}
}
