package org.geepawhill.contentment;

import static org.geepawhill.contentment.MarkedTestStep.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import javafx.scene.layout.Pane;

public class PlayerTest
{

	private Player player;
	Pane canvas;

	@Before
	public void before()
	{
		canvas = new Pane();
		player = new Player(canvas);
	}
	
	@Test
	public void newIsEmpty()
	{
		assertEquals(0,player.size());
	}
	
	@Test
	public void newIsBeforeAll()
	{
		assertEquals(0,player.current());
		assertBefore();
	}
	
	@Test
	public void loadLoads()
	{
		player.reset(oneStepSequence);
		assertEquals(1,player.size());
	}
	
	@Test
	public void loadBeforesAll()
	{
		oneStep.isBefore=false;
		player.reset(oneStepSequence);
		assertTrue(oneStep.isBefore);
		assertBefore();
	}

	@Test
	public void forwardStopsOnMarkedStep()
	{
		UnmarkedTestStep unmarked = new UnmarkedTestStep();
		Sequence mixedSequence = new Sequence(oneStep,unmarked,twoStep);
		player.reset(mixedSequence);
		player.forward();
		assertEquals(2,player.current());
		assertFalse(oneStep.isBefore);
		assertFalse(unmarked.isBefore);
		assertTrue(twoStep.isBefore);
		assertBefore();
	}
	
	@Test
	public void forwardWhileBeforeSteps()
	{
		player.reset(twoStepSequence);
		player.forward();
		assertEquals(1,player.current());
		assertFalse(oneStep.isBefore);
		assertBefore();
	}
	
	@Test
	public void forwardWhilePlayingSteps()
	{
		player.reset(twoStepSequence);
		player.play();
		player.forward();
		assertEquals(1,player.current());
		assertFalse(oneStep.isBefore);
		assertTrue(twoStep.isBefore);
		assertBefore();
	}
	
	@Test
	public void forwardWhilePausedSteps()
	{
		player.reset(twoStepSequence);
		player.play();
		player.pause();
		player.forward();
		assertEquals(1,player.current());
		assertFalse(oneStep.isBefore);
		assertTrue(twoStep.isBefore);
		assertBefore();
	}

	
	@Test
	public void forwardNoopsAtEnd()
	{
		player.reset(oneStepSequence);
		player.forward();
		player.forward();
		assertEquals(0,player.current());
		assertFalse(oneStep.isBefore);
		assertAfter();
	}

	@Test
	public void backwardStopsOnMarkedStep()
	{
		UnmarkedTestStep unmarked = new UnmarkedTestStep();
		Sequence mixedSequence = new Sequence(oneStep,unmarked,twoStep);
		player.reset(mixedSequence);
		player.forward();
		player.backward();
		assertEquals(0,player.current());
		assertTrue(oneStep.isBefore);
		assertTrue(unmarked.isBefore);
		assertTrue(twoStep.isBefore);
		assertBefore();
	}
	
	@Test
	public void backwardWhileBeforeGoesBackward()
	{
		player.reset(twoStepSequence);
		player.play();
		oneStep.finishPlaying(player.context);
		player.backward();
		assertEquals(1,player.current());
		assertTrue(twoStep.isBefore);
		assertBefore();
	}
	
	@Test
	public void backwardWhileAfterDoesBefore()
	{
		player.reset(twoStepSequence);
		player.forward();
		player.forward();
		player.backward();
		assertEquals(1,player.current());
		assertTrue(twoStep.isBefore);
		assertBefore();
	}
	
	@Test
	public void backwardWhilePlayingDoesBefore()
	{
		player.reset(twoStepSequence);
		player.play();
		oneStep.finishPlaying(player.context);
		player.backward();
		assertEquals(1,player.current());
		assertTrue(twoStep.isBefore);
		assertBefore();
	}
	
	@Test
	public void backwardWhilePausedDoesBefore()
	{
		player.reset(twoStepSequence);
		player.play();
		oneStep.finishPlaying(player.context);
		player.pause();
		player.backward();
		assertEquals(1,player.current());
		assertTrue(twoStep.isBefore);
		assertBefore();
	}
	
	@Test
	public void backwardackwardNoopsAtBeginning()
	{
		player.reset(oneStepSequence);
		player.backward();
		assertEquals(0,player.current());
		assertTrue(oneStep.isBefore);
	}
	
	@Test
	public void pauseBeforeNoops()
	{
		player.reset(oneStepSequence);
		player.pause();
		assertTrue(oneStep.isBefore);
	}
	
	@Test
	public void pauseWhilePlayingPauses()
	{
		player.reset(oneStepSequence);
		player.play();
		player.pause();
		assertTrue(oneStep.isPaused);
		assertPaused();
	}
	
	@Test
	public void pauseWhilePausedPlays()
	{
		player.reset(oneStepSequence);
		player.play();
		player.pause();
		assertTrue(oneStep.isPaused);
		assertPaused();
		player.pause();
		assertFalse(oneStep.isPaused);
		assertPlaying();
	}

	@Test
	public void pauseAfterNoops()
	{
		player.reset(oneStepSequence);
		player.forward();
		oneStep.isChanged=false;
		player.pause();
		assertFalse(oneStep.isChanged);
	}
	
	@Test
	public void seekNoChangeDoesBefore()
	{
		player.reset(oneStepSequence);
		player.forward();
		player.seek(0);
		assertEquals(0,player.current());
		assertTrue(oneStep.isBefore);
	}
	
	@Test
	public void seekForward()
	{
		player.reset(twoStepSequence);
		player.seek(1);
		assertEquals(1,player.current());
		assertFalse(oneStep.isBefore);
		assertTrue(twoStep.isBefore);
	}
	
	@Test
	public void seekBackward()
	{
		player.reset(twoStepSequence);
		player.forward();
		player.forward();
		player.seek(0);
		assertEquals(0,player.current());
		assertTrue(oneStep.isBefore);
		assertTrue(twoStep.isBefore);
	}
	
	@Test
	public void seekCurrentResets()
	{
		player.reset(twoStepSequence);
		oneStep.isBefore=false;
		player.seek(0);
		assertEquals(0,player.current());
		assertTrue(oneStep.isBefore);
		assertTrue(twoStep.isBefore);
	}
	
	@Test
	public void seekBeforeStart()
	{
		player.reset(twoStepSequence);
		player.forward();
		player.forward();
		player.seek(-20);
		assertEquals(0,player.current());
		assertTrue(oneStep.isBefore);
		assertTrue(twoStep.isBefore);		
	}
	
	@Test
	public void seekAfterEnd()
	{
		player.reset(twoStepSequence);
		player.seek(20);
		assertEquals(1,player.current());
		assertFalse(oneStep.isBefore);
		assertFalse(twoStep.isBefore);		
		assertEquals(PlayState.After,player.state());
	}
	
	@Test
	public void seekWhilePlayingStops()
	{
		player.reset(twoStepSequence);
		player.play();
		player.seek(1);
		assertEquals(1,player.current());
		assertFalse(oneStep.isBefore);
		assertFalse(oneStep.isPlaying);
		assertTrue(twoStep.isBefore);
		assertFalse(twoStep.isPlaying);
	}

	@Test
	public void seekWhilePausedStops()
	{
		player.reset(twoStepSequence);
		player.play();
		player.pause();
		player.seek(1);
		assertEquals(1,player.current());
		assertFalse(oneStep.isBefore);
		assertFalse(oneStep.isPlaying);
		assertTrue(twoStep.isBefore);
		assertFalse(twoStep.isPlaying);
	}

	@Test
	public void playWhileBeforePlays()
	{
		player.reset(oneStepSequence);
		player.play();
		assertTrue(oneStep.isPlaying);
		assertPlaying();
	}
	
	@Test
	public void playWhilePlayingNoops()
	{
		player.reset(oneStepSequence);
		player.play();
		assertTrue(oneStep.isPlaying);
		assertTrue(oneStep.isChanged);
		oneStep.isChanged = false;
		player.play();
		assertFalse(oneStep.isChanged);
	}
	
	@Test
	public void playWhilePausedResumes()
	{
		player.reset(oneStepSequence);
		player.play();
		assertTrue(oneStep.isPlaying);
		assertTrue(oneStep.isChanged);
		player.pause();
		assertTrue(oneStep.isPaused);
		player.play();
		assertTrue(oneStep.isPlaying);
	}
	
	@Test
	public void playWhileAfterNoops()
	{
		player.reset(oneStepSequence);
		player.play();
		oneStep.finishPlaying(player.context);
		oneStep.isChanged=false;
		player.play();
		assertFalse(oneStep.isChanged);
		assertAfter();
	}
	
	@Test
	public void playChainsToAllSteps()
	{
		player.reset(twoStepSequence);
		player.play();
		oneStep.finishPlaying(player.context);
		assertTrue(twoStep.isPlaying);
		twoStep.finishPlaying(player.context);
		assertAfter();
	}
	
	@Test
	public void playOneDoesNotChain()
	{
		player.reset(twoStepSequence);
		player.playOne();
		oneStep.finishPlaying(player.context);
		assertFalse(twoStep.isPlaying);
		assertEquals(1,player.current());
		assertBefore();
	}
	
	@Test
	public void playOneStopsChainingWhilePlaying()
	{
		player.reset(twoStepSequence);
		player.play();
		player.playOne();
		oneStep.finishPlaying(player.context);
		assertFalse(twoStep.isPlaying);
		assertEquals(1,player.current());
		assertBefore();
	}

	private void assertAfter()
	{
		assertEquals(PlayState.After,player.state());
	}

	private void assertPlaying()
	{
		assertEquals(PlayState.Playing,player.state());
	}
	
	private void assertBefore()
	{
		assertEquals(PlayState.Before,player.state());
	}
	
	private void assertPaused()
	{
		assertEquals(PlayState.Paused,player.state());
	}

}
