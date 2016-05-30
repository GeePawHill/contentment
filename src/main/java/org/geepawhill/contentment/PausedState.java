package org.geepawhill.contentment;

public class PausedState implements PlayerState {

	@Override
	public PlayerState onPlay(Context context) {
		context.stepper.resume();
		return context.playing;
	}

	@Override
	public PlayerState onPause(Context context) {
		return context.paused;
	}

	@Override
	public PlayerState onResume(Context context) {
		context.stepper.resume();
		return context.playing;
	}

	@Override
	public PlayerState onStop(Context context) {
		context.stepper.stop();
		return context.stopped;
	}

}
