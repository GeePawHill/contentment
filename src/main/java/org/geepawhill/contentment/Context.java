package org.geepawhill.contentment;

import javafx.scene.layout.Pane;

public class Context {
	public final PlayerState playing;
	public final PausedState paused;
	public final StoppedState stopped;
	
	public final Stepper stepper;
	public final Pane canvas;
	
	public Context(Pane canvas)
	{
		this.canvas = canvas;
		playing = new PlayingState();
		paused = new PausedState();
		stopped = new StoppedState();
		stepper = new Stepper();
	}
}
