package org.geepawhill.contentment.core;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;
import java.time.LocalDateTime;

import org.geepawhill.contentment.rhythm.Rhythm;
import org.geepawhill.contentment.rhythm.SimpleRhythm;
import org.geepawhill.contentment.step.SyncStep;
import org.geepawhill.contentment.test.TestPhrase;
import org.geepawhill.contentment.timing.Timing;
import org.junit.Before;
import org.junit.Test;

import javafx.scene.Group;

public class SyncPlayerTest
{
	
	private Script script;
	private SyncPlayer player;
	private TestPhrase first;
	private TestPhrase second;
	private TestPhrase third;

	
	@Before
	public void before()
	{
		script = new Script();

		first = new TestPhrase(Timing.ms(100d));
		script.add(new SyncStep(0,first));
		
		second = new TestPhrase(Timing.ms(100d));
		script.add(new SyncStep(500,second));
		
		third = new TestPhrase(Timing.ms(100d));
		script.add(new SyncStep(700,third));
		
		Group canvas = new Group();
		Rhythm rhythm = new SimpleRhythm();
		player = new SyncPlayer(canvas,rhythm);
	}

	@Test
	public void loadPositionsAtZero()
	{
		player.load(script);
		assertThat(player.getState()).isEqualTo(SyncPlayer.State.Stepping);
		assertThat(player.atStart()).isEqualTo(true);
		assertThat(player.beat()).isEqualTo(0);
	}
	
	@Test
	public void forwardForwards()
	{
		player.load(script);
		player.forward();
		assertThat(first.isBefore).isFalse();
		assertThat(player.getState()).isEqualTo(SyncPlayer.State.Stepping);
		assertThat(player.position()).isEqualTo(1);
		assertThat(player.beat()).isEqualTo(500);
	}
	
	@Test
	public void forwardAtEndNoOps()
	{
		player.load(script);
		player.end();
		player.forward();
		assertThat(player.atEnd()).isTrue();
		assertThat(player.beat()).isEqualTo(Rhythm.MAX);
		assertThat(player.getState()).isEqualTo(SyncPlayer.State.Stepping);
	}
	
	@Test
	public void playOneAtEndNoOps()
	{
		player.load(script);
		player.end();
		assertThat(player.atEnd()).isTrue();
		player.playOne();
		assertThat(player.atEnd()).isTrue();
		assertThat(player.beat()).isEqualTo(Rhythm.MAX);
		assertThat(player.getState()).isEqualTo(SyncPlayer.State.Stepping);
	}
	
	@Test
	public void backwardBackwards()
	{
		player.load(script);
		player.forward();
		player.backward();
		assertThat(first.isBefore).isTrue();
		assertThat(second.isBefore).isTrue();
		assertThat(player.atStart()).isTrue();
		assertThat(player.beat()).isEqualTo(0);
		assertThat(player.getState()).isEqualTo(SyncPlayer.State.Stepping);
	}
	
	@Test
	public void backwardAtBeginningNoOps()
	{
		player.load(script);
		player.backward();
		assertThat(player.atStart()).isTrue();
		assertThat(player.beat()).isEqualTo(0);
		assertThat(player.getState()).isEqualTo(SyncPlayer.State.Stepping);
	}
	
	@Test
	public void backwardAtEndWorks()
	{
		player.load(script);
		player.end();
		player.backward();
		assertThat(player.position()).isEqualTo(2);
		assertThat(player.beat()).isEqualTo(700);
		assertThat(player.getState()).isEqualTo(SyncPlayer.State.Stepping);
	}
	
	@Test
	public void playOneDoes()
	{
		player.load(script);
		player.playOne();
		assertThat(player.getState()).isEqualTo(SyncPlayer.State.Playing);
		assertThat(first.isPlaying).isTrue();
		first.finishPlaying();
		assertThat(player.getState()).isEqualTo(SyncPlayer.State.Stepping);
		assertThat(first.isBefore).isFalse();
		assertThat(player.position()).isEqualTo(1);
	}
	
	@Test
	public void playOneThroughEndDoes()
	{
		player.load(script);
		player.playOne();
		first.finishPlaying();
		player.playOne();
		second.finishPlaying();
		player.playOne();
		third.finishPlaying();
		assertThat(player.getState()).isEqualTo(SyncPlayer.State.Stepping);
		assertThat(player.position()).isEqualTo(3);
	}
	
	@Test
	public void playOneHandlesRhythm()
	{
		player.load(script);
		player.playOne();
		assertThat(player.getRhythm().isPlaying()).isTrue();
		first.finishPlaying();
		assertThat(player.getRhythm().isPlaying()).isFalse();
		assertThat(first.isBefore).isFalse();
	}
	
	@Test
	public void playPlaysAll()
	{
		player.load(script);
		player.play();
		first.finishPlaying();
		second.finishPlaying();
		third.finishPlaying();
		assertThat(player.getState()).isEqualTo(SyncPlayer.State.Stepping);
		assertThat(player.atEnd()).isTrue();
	}
	
	@Test
	public void playHandlesRhythm()
	{
		player.load(script);
		player.play();
		assertThat(player.getRhythm().isPlaying()).isTrue();
		first.finishPlaying();
		second.finishPlaying();
		third.finishPlaying();
		assertThat(player.getRhythm().isPlaying()).isFalse();
	}

	@Test
	public void end()
	{
		player.load(script);
		player.end();
		assertThat(player.atEnd()).isTrue();
		assertThat(player.beat()).isEqualTo(Rhythm.MAX);
	}
	
	@Test
	public void start()
	{
		player.load(script);
		player.forward();
		player.start();
		assertThat(player.atStart()).isTrue();
		assertThat(first.isBefore).isTrue();
		assertThat(player.getBeat()).isEqualTo(0);
	}
	
	@Test
	public void last()
	{
		player.load(script);
		player.last();
		assertThat(player.position()).isEqualTo(2);
	}
	
	@Test
	public  void playOneTakesFullTime()
	{
		player.load(script);
		LocalDateTime start = LocalDateTime.now();
		player.playOne();
		first.finishPlaying();
		while(player.getState()==SyncPlayer.State.Playing);
		Duration elapsed = Duration.between(start,LocalDateTime.now());
		assertThat(elapsed.toMillis()).isBetween(500L, 600L);
	}
	
	@Test
	public void playTakesFullTime()
	{
		player.load(script);
		LocalDateTime start = LocalDateTime.now();
		player.play();
		first.finishPlaying();
		second.finishPlaying();
		third.finishPlaying();
		while(player.getState()==SyncPlayer.State.Playing);
		Duration elapsed = Duration.between(start,LocalDateTime.now());
		assertThat(elapsed.toMillis()).isBetween(700L,770L);
	}
}
