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

	@Override
	public PlayerState onResume(Context context) {
		return context.playing;
	}

	@Override
	public PlayerState onStop(Context context) {
		context.stepper.stop();
		return context.stopped;
	}

}
