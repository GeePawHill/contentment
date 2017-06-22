package org.geepawhill.contentment.step;

import static org.assertj.core.api.Assertions.assertThat;

import org.geepawhill.contentment.core.Sequence;
import org.geepawhill.contentment.rhythm.Rhythm;
import org.geepawhill.contentment.rhythm.SimpleRhythm;
import org.junit.Before;
import org.junit.Test;

import javafx.scene.Group;

public class SyncPlayerTest
{
	
	private Sequence sequence;
	private SyncPlayer player;

	@Before
	public void before()
	{
		sequence = new Sequence();
		sequence.add(new SyncStep(300,100));
		sequence.add(new SyncStep(500,100));
		sequence.add(new SyncStep(700,100));
		Group canvas = new Group();
		Rhythm rhythm = new SimpleRhythm();
		player = new SyncPlayer(canvas,rhythm);
	}

	@Test
	public void loadPositionsAtZero()
	{
		player.load(sequence);
		assertThat(player.getNext()).isEqualTo(0);
		assertThat(player.beat()).isEqualTo(0);
	}
	
	@Test
	public void forwardForwards()
	{
		player.load(sequence);
		player.forward();
		assertThat(player.getNext()).isEqualTo(1);
		assertThat(player.beat()).isEqualTo(500);
	}
	
	@Test
	public void forwardAtEndNoOps()
	{
		player.load(sequence);
		player.forward();
		player.forward();
		player.forward();
		assertThat(player.getNext()).isEqualTo(3);
		assertThat(player.beat()).isEqualTo(800);
	}
	
	@Test
	public void backwardBackwards()
	{
		player.load(sequence);
		player.forward();
		player.forward();
		player.backward();
		assertThat(player.getNext()).isEqualTo(1);
		assertThat(player.beat()).isEqualTo(500);
	}
	
	@Test
	public void backwardAtBeginningNoOps()
	{
		player.load(sequence);
		player.backward();
		assertThat(player.getNext()).isEqualTo(0);
		assertThat(player.beat()).isEqualTo(0);
	}

}
