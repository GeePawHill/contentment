package org.geepawhill.contentment.step;

import static org.assertj.core.api.Assertions.assertThat;

import org.geepawhill.contentment.rhythm.Rhythm;
import org.geepawhill.contentment.rhythm.SimpleRhythm;
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
		script.add(new SyncStep(300,100,first));
		
		second = new TestPhrase(Timing.ms(100d));
		script.add(new SyncStep(500,100,second));
		
		third = new TestPhrase(Timing.ms(100d));
		script.add(new SyncStep(700,100,third));
		
		Group canvas = new Group();
		Rhythm rhythm = new SimpleRhythm();
		player = new SyncPlayer(canvas,rhythm);
	}

	@Test
	public void loadPositionsAtZero()
	{
		player.load(script);
		assertThat(player.getState()).isEqualTo(SyncPlayer.State.Stepping);
		assertThat(player.getNext()).isEqualTo(0);
		assertThat(player.beat()).isEqualTo(0);
	}
	
	@Test
	public void forwardForwards()
	{
		player.load(script);
		player.forward();
		assertThat(player.getState()).isEqualTo(SyncPlayer.State.Stepping);
		assertThat(player.getNext()).isEqualTo(1);
		assertThat(player.beat()).isEqualTo(500);
	}
	
	@Test
	public void forwardAtEndNoOps()
	{
		player.load(script);
		player.forward();
		System.out.println(player.getNext());
		player.forward();
		player.forward();
		assertThat(player.getNext()).isEqualTo(3);
		assertThat(player.beat()).isEqualTo(800);
		assertThat(player.getState()).isEqualTo(SyncPlayer.State.Stepping);
	}
	
	@Test
	public void backwardBackwards()
	{
		player.load(script);
		player.forward();
		player.forward();
		player.backward();
		assertThat(player.getNext()).isEqualTo(1);
		assertThat(player.beat()).isEqualTo(500);
		assertThat(player.getState()).isEqualTo(SyncPlayer.State.Stepping);
	}
	
	@Test
	public void backwardAtBeginningNoOps()
	{
		player.load(script);
		player.backward();
		assertThat(player.getNext()).isEqualTo(0);
		assertThat(player.beat()).isEqualTo(0);
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
		assertThat(player.getNext()).isEqualTo(1);
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
		assertThat(player.getNext()).isEqualTo(3);
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
		assertThat(player.getNext()).isEqualTo(3);
	}

}
