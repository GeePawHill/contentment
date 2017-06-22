package org.geepawhill.contentment.rhythm;

import java.time.Duration;
import java.time.LocalDateTime;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;

public class SimpleRhythm implements Rhythm
{
	private SimpleLongProperty beatProperty;
	private boolean isPlaying;
	private LocalDateTime startedPlayingAt;
	private long startedPauseAt;
	
	
	public SimpleRhythm()
	{
		beatProperty = new SimpleLongProperty(0L);
		isPlaying=false;
		startedPauseAt=0L;
	}

	@Override
	public LongProperty beatProperty()
	{
		return beatProperty;
	}
	
	@Override
	public long beat()
	{
		update();
		return beatProperty.get();
	}

	@Override
	public void seekHard(long ms)
	{
		if(isPlaying) pause();
		beatProperty.set(ms);
		startedPauseAt = ms;
	}
	
	@Override
	public void seekSoft(long ms)
	{
		if(beat()<ms) seekHard(ms);
	}
	
	@Override
	public void update()
	{
		long newBeat = getPlayerTime();
		beatProperty.set(newBeat);
	}

	private long getPlayerTime()
	{
		if(isPlaying) return startedPauseAt+Duration.between( startedPlayingAt, LocalDateTime.now()).toMillis();
		return startedPauseAt;
	}

	@Override
	public void play()
	{
		if(isPlaying) throw new RuntimeException("Can't play when already playing.");
		startedPlayingAt = LocalDateTime.now();
		isPlaying=true;
		update();
	}

	@Override
	public void pause()
	{
		if(!isPlaying) throw new RuntimeException("Can't pause when not playing.");
		startedPauseAt = beat();
		isPlaying=false;
	}
}
