package org.geepawhill.contentment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import javafx.scene.layout.Pane;

import static org.geepawhill.contentment.TestStep.*;

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
	public void stepForwardDoes()
	{
		player.reset(oneStepSequence);
		player.stepForward();
		assertEquals(1,player.current());
		assertFalse(oneStep.isBefore);
		assertBefore();
	}
	
	@Test
	public void stepBackwardDoes()
	{
		player.reset(oneStepSequence);
		player.stepForward();
		player.stepBackward();
		assertEquals(0,player.current());
		assertTrue(oneStep.isBefore);
		assertBefore();
	}
	
	@Test
	public void stepForwardNoopsAtEnd()
	{
		player.reset(oneStepSequence);
		player.stepForward();
		player.stepForward();
		assertEquals(1,player.current());
		assertFalse(oneStep.isBefore);
	}
	
	@Test
	public void stepBackwardNoopsAtBeginning()
	{
		player.reset(oneStepSequence);
		player.stepBackward();
		assertEquals(0,player.current());
		assertTrue(oneStep.isBefore);
	}
	
	@Test
	public void seekNoChangeDoesBefore()
	{
		player.reset(oneStepSequence);
		player.stepForward();
		player.seek(canvas, 0);
		assertEquals(0,player.current());
		assertTrue(oneStep.isBefore);
	}
	
	@Test
	public void seekForward()
	{
		player.reset(twoStepSequence);
		player.seek(canvas, 1);
		assertEquals(1,player.current());
		assertFalse(oneStep.isBefore);
		assertTrue(twoStep.isBefore);
	}
	
	@Test
	public void seekBackward()
	{
		player.reset(twoStepSequence);
		player.stepForward();
		player.stepForward();
		player.seek(canvas, 0);
		assertEquals(0,player.current());
		assertTrue(oneStep.isBefore);
		assertTrue(twoStep.isBefore);
	}
	
	@Test
	public void seekCurrentResets()
	{
		player.reset(twoStepSequence);
		oneStep.isBefore=false;
		player.seek(canvas, 0);
		assertEquals(0,player.current());
		assertTrue(oneStep.isBefore);
		assertTrue(twoStep.isBefore);
	}
	
	@Test
	public void seekBeforeStart()
	{
		player.reset(twoStepSequence);
		player.stepForward();
		player.stepForward();
		player.seek(canvas, -20);
		assertEquals(0,player.current());
		assertTrue(oneStep.isBefore);
		assertTrue(twoStep.isBefore);		
	}
	
	@Test
	public void seekAfterEnd()
	{
		player.reset(twoStepSequence);
		player.seek(canvas, 20);
		assertEquals(1,player.current());
		assertFalse(oneStep.isBefore);
		assertFalse(twoStep.isBefore);		
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

	private void assertAfter()
	{
		assertEquals(PlayState.After,player.getState());
	}

	private void assertPlaying()
	{
		assertEquals(PlayState.Playing,player.getState());
	}
	
	private void assertBefore()
	{
		assertEquals(PlayState.Before,player.getState());
	}

}
