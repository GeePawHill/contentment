package org.geepawhill.contentment.core;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.geepawhill.contentment.model.PlayState;
import org.geepawhill.contentment.test.JavaFxTest;
import org.geepawhill.contentment.test.TestStep;
import org.geepawhill.contentment.test.TestStop;
import org.junit.Before;
import org.junit.Test;

import javafx.scene.Group;

public class PlayerTest extends JavaFxTest
{
	TestStep nonStopFirst;
	TestStop stopFirst;
	TestStep nonStopSecond;
	TestStop stopSecond;
	TestStep nonStopThird;

	Sequence mixedSequence;

	private Player player;
	Group canvas;

	@Before
	public void before()
	{
		nonStopFirst = new TestStep();
		stopFirst = new TestStop();
		nonStopSecond = new TestStep();
		stopSecond = new TestStop();
		nonStopThird = new TestStep();
		mixedSequence = new Sequence().add(nonStopFirst).add(stopFirst).add(nonStopSecond).add( stopSecond).add( nonStopThird);
		canvas = new Group();
		player = new Player(canvas);
	}

	@Test
	public void newIsEmpty()
	{
		assertThat(player.size()).isEqualTo(0);
		assertThat(player.current()).isEqualTo(0);
		assertBefore();
		assertThat(player.isPlaying()).isFalse();
	}
	
	@Test
	public void resetIsBeforeAll()
	{
		player.reset(mixedSequence);
		assertBefore();
	}

	@Test
	public void resetChangesCount()
	{
		player.reset(mixedSequence);
		assertEquals(5, player.size());
	}
	
	@Test
	public void playStartsClock()
	{
		player.reset(mixedSequence);
		player.play();
		assertThat(player.isPlaying()).isTrue();
	}

	@Test
	public void playGoesToEnd()
	{
		player.reset(mixedSequence);
		player.play();
		nonStopFirst.finishPlaying();
		stopFirst.finishPlaying();
		nonStopSecond.finishPlaying();
		stopSecond.finishPlaying();
		nonStopThird.finishPlaying();
		assertAfter();
	}

	@Test
	public void forwardBreaksPlaying()
	{
		player.reset(new Sequence().add(nonStopFirst));
		player.play();
		assertTrue(nonStopFirst.isPlaying);
		player.forward();
		assertFalse(nonStopFirst.isPlaying);
		assertFalse(nonStopFirst.isBefore);
	}

	@Test
	public void backwardBreaksPlaying()
	{
		player.reset(new Sequence().add(nonStopFirst));
		player.play();
		assertTrue(nonStopFirst.isPlaying);
		player.backward();
		assertFalse(nonStopFirst.isPlaying);
		assertTrue(nonStopFirst.isBefore);
	}

	@Test
	public void forwardBeforeStopGoesToBeforeNextStop()
	{
		player.reset(new Sequence().add(nonStopFirst).add( stopFirst));
		player.forward();
		assertFalse(nonStopFirst.isPlaying);
		assertFalse(nonStopFirst.isBefore);
		assertTrue(stopFirst.isBefore);
		assertEquals(stopFirst, player.currentStep());
	}

	@Test
	public void playOneBeforeStopGoesToBeforeNextStop()
	{
		player.reset(new Sequence().add(nonStopFirst).add( stopFirst));
		player.playOne();
		nonStopFirst.finishPlaying();
		assertFalse(nonStopFirst.isPlaying);
		assertFalse(nonStopFirst.isBefore);
		assertTrue(stopFirst.isBefore);
		assertEquals(stopFirst, player.currentStep());
	}

	@Test
	public void forwardOnStopGoesToBeforeNextStop()
	{
		player.reset(new Sequence().add(stopFirst).add( nonStopFirst).add( stopSecond));
		player.forward();
		assertFalse(stopFirst.isPlaying);
		assertFalse(stopFirst.isBefore);
		assertFalse(nonStopFirst.isPlaying);
		assertFalse(nonStopFirst.isBefore);
		assertTrue(stopSecond.isBefore);
		assertEquals(stopSecond, player.currentStep());
	}

	@Test
	public void playOneOnStopGoesToBeforeNextStop()
	{
		player.reset(new Sequence().add(stopFirst).add( nonStopFirst).add( stopSecond));
		player.playOne();
		stopFirst.finishPlaying();
		nonStopFirst.finishPlaying();
		assertFalse(stopFirst.isPlaying);
		assertFalse(stopFirst.isBefore);
		assertFalse(nonStopFirst.isPlaying);
		assertFalse(nonStopFirst.isBefore);
		assertTrue(stopSecond.isBefore);
		assertEquals(stopSecond, player.currentStep());
	}

	@Test
	public void forwardOnLastStopGoesToEnd()
	{
		player.reset(new Sequence().add(stopFirst).add(nonStopSecond).add( nonStopThird));
		player.forward();
		assertFalse(stopFirst.isPlaying);
		assertFalse(stopFirst.isBefore);
		assertFalse(nonStopSecond.isPlaying);
		assertFalse(nonStopSecond.isBefore);
		assertFalse(nonStopThird.isPlaying);
		assertEquals(nonStopThird, player.currentStep());
		assertFalse(nonStopThird.isBefore);
		assertAfter();
	}

	@Test
	public void backwardWithNoStopGoesToZero()
	{
		player.reset(new Sequence().add(nonStopFirst).add( nonStopSecond));
		player.forward();
		player.backward();
		assertFalse(nonStopSecond.isPlaying);
		assertTrue(nonStopSecond.isBefore);
		assertFalse(nonStopFirst.isPlaying);
		assertTrue(nonStopFirst.isBefore);
		assertEquals(nonStopFirst, player.currentStep());
		assertBefore();
	}

	@Test
	public void backwardOnStopGoesBeforePreviousStop()
	{
		player.reset(new Sequence().add(stopFirst).add( stopSecond));
		player.forward();
		player.backward();
		assertFalse(stopSecond.isPlaying);
		assertTrue(stopSecond.isBefore);
		assertFalse(stopFirst.isPlaying);
		assertTrue(stopFirst.isBefore);
		assertEquals(stopFirst, player.currentStep());
		assertBefore();
	}

	@Test
	public void backwardOnNonStopGoesBeforePreviousStop()
	{
		player.reset(new Sequence().add(stopFirst).add( nonStopSecond));
		player.forward();
		player.backward();
		assertFalse(nonStopSecond.isPlaying);
		assertTrue(nonStopSecond.isBefore);
		assertFalse(stopFirst.isPlaying);
		assertTrue(stopFirst.isBefore);
		assertEquals(stopFirst, player.currentStep());
		assertBefore();
	}

	@Test
	public void backwardBeforeStopGoesBeforePreviousStop()
	{
		player.reset(new Sequence().add(stopFirst).add( nonStopFirst).add( stopSecond));
		player.forward();
		player.backward();
		assertFalse(stopSecond.isPlaying);
		assertTrue(stopSecond.isBefore);
		assertFalse(nonStopFirst.isPlaying);
		assertTrue(nonStopFirst.isBefore);
		assertFalse(stopFirst.isPlaying);
		assertTrue(stopFirst.isBefore);
		assertEquals(stopFirst, player.currentStep());
		assertBefore();
	}

	@Test
	public void backwardSkGoesBeforePreviousStop()
	{
		player.reset(new Sequence().add(stopFirst).add( stopSecond));
		player.forward();
		player.backward();
		assertFalse(stopSecond.isPlaying);
		assertTrue(stopSecond.isBefore);
		assertFalse(stopFirst.isPlaying);
		assertTrue(stopFirst.isBefore);
		assertEquals(stopFirst, player.currentStep());
		assertBefore();
	}

	@Test
	public void playOneOnLastStopGoesToEnd()
	{
		player.reset(new Sequence().add(stopFirst).add( nonStopSecond).add( nonStopThird));
		player.playOne();
		stopFirst.finishPlaying();
		nonStopSecond.finishPlaying();
		nonStopThird.finishPlaying();
		assertFalse(stopFirst.isPlaying);
		assertFalse(stopFirst.isBefore);
		assertFalse(nonStopSecond.isPlaying);
		assertFalse(nonStopSecond.isBefore);
		assertFalse(nonStopThird.isPlaying);
		assertEquals(nonStopThird, player.currentStep());
		assertFalse(nonStopThird.isBefore);
		assertAfter();
	}

	@Test
	public void forwardNoopsAtEnd()
	{
		player.reset(new Sequence().add(nonStopFirst));
		player.forward();
		assertEquals(nonStopFirst, player.currentStep());
		assertFalse(nonStopFirst.isBefore);
		assertAfter();
		player.forward();
		assertEquals(nonStopFirst, player.currentStep());
		assertFalse(nonStopFirst.isBefore);
		assertAfter();
	}

	@Test
	public void backwardNoopsAtBegin()
	{
		player.reset(new Sequence().add(nonStopFirst));
		player.backward();
		assertEquals(nonStopFirst, player.currentStep());
		assertTrue(nonStopFirst.isBefore);
		assertBefore();
	}

	@Test
	public void playOneStopsChainingWhilePlaying()
	{
		player.reset(new Sequence().add(nonStopFirst).add( nonStopSecond).add( stopFirst));
		player.play();
		nonStopFirst.finishPlaying();
		assertTrue(nonStopSecond.isPlaying);
		player.playOne();
		nonStopSecond.finishPlaying();
		assertFalse(nonStopSecond.isPlaying);
		assertTrue(stopFirst.isBefore);
		assertEquals(stopFirst, player.currentStep());
		assertBefore();
	}

	private void assertAfter()
	{
		assertThat(player.state()).isEqualTo(PlayState.After);
	}

	private void assertBefore()
	{
		assertThat(player.state()).isEqualTo(PlayState.Before);
	}
}
