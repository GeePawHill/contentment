package org.geepawhill.contentment;

public class PlayingState implements PlayerState {

	@Override
	public PlayerState onPlay(Context context) {
		return context.playing;
	}

	@Override
	public PlayerState onPause(Context context) {
		context.stepper.pause();
		return context.paused;
	}

}
