package org.geepawhill.contentment;

public class StoppedState implements PlayerState {

	@Override
	public PlayerState onPlay(Context context) {
		context.stepper.play();
		return context.playing;
	}

	@Override
	public PlayerState onPause(Context context) {
		return context.stopped;
	}

	@Override
	public PlayerState onResume(Context context) {
		return context.stopped;
	}

	@Override
	public PlayerState onStop(Context context) {
		return context.stopped;
	}

}
