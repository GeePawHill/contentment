package org.geepawhill.contentment;

public interface PlayerState {
	public PlayerState onPlay(Context context);
	public PlayerState onPause(Context context);
	public PlayerState onResume(Context context);
	public PlayerState onStop(Context context);
}
