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
	}
	
	@Test
	public void stepForwardDoes()
	{
		player.reset(oneStepSequence);
		player.stepForward();
		assertEquals(1,player.current());
		assertFalse(oneStep.isBefore);
	}
	
	@Test
	public void stepBackwardDoes()
	{
		player.reset(oneStepSequence);
		player.stepForward();
		player.stepBackward();
		assertEquals(0,player.current());
		assertTrue(oneStep.isBefore);
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
	
}
