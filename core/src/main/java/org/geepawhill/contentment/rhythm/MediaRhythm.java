package org.geepawhill.contentment.rhythm;

import java.io.File;
import java.time.Duration;
import java.time.LocalDateTime;

import javafx.animation.AnimationTimer;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MediaRhythm implements Rhythm
{
	private SimpleLongProperty beatProperty;
	private boolean isPlaying;
	private LocalDateTime startedPlayingAt;
	private long startedPauseAt;

	private AnimationTimer timer;
	private MediaPlayer mediaPlayer;

	public MediaRhythm()
	{
		Media m = new Media(new File("/01faceoverCut.mp4").toURI().toString());
		mediaPlayer = new MediaPlayer(m);
		mediaPlayer.pause();
		beatProperty = new SimpleLongProperty(0L);
		isPlaying = false;
		startedPauseAt = 0L;
		timer = new AnimationTimer()
		{

			@Override
			public void handle(long now)
			{
				update();

			}
		};
	}

	public MediaPlayer getMediaPlayer()
	{
		return mediaPlayer;
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
		if (isPlaying) pause();
		if (ms == Rhythm.MAX)
		{
			mediaPlayer.seek(javafx.util.Duration.seconds(mediaPlayer.getTotalDuration().toSeconds()-1d));
			mediaPlayer.seek(javafx.util.Duration.INDEFINITE);
			System.out.println("Seek to end.");
		}
		else
		{
			mediaPlayer.seek(javafx.util.Duration.millis((double) ms));
		}
		beatProperty.set(ms);
		startedPauseAt = ms;
	}

	@Override
	public void update()
	{
		long newBeat = getPlayerTime();
		beatProperty.set(newBeat);
	}

	private long getPlayerTime()
	{
		if (isPlaying) return startedPauseAt + Duration.between(startedPlayingAt, LocalDateTime.now()).toMillis();
		return startedPauseAt;
	}

	@Override
	public void play()
	{
		if (isPlaying) throw new RuntimeException("Can't play when already playing.");
		mediaPlayer.play();
		startedPlayingAt = LocalDateTime.now();
		isPlaying = true;
		timer.start();
	}

	@Override
	public void pause()
	{
		if (!isPlaying) throw new RuntimeException("Can't pause when not playing.");
		mediaPlayer.pause();
		timer.stop();
		startedPauseAt = beat();
		isPlaying = false;
	}

	@Override
	public boolean isPlaying()
	{
		return isPlaying;
	}

	@Override
	public boolean isAtEnd()
	{
		return mediaPlayer.getCurrentTime().equals(mediaPlayer.getCycleDuration());
	}

}