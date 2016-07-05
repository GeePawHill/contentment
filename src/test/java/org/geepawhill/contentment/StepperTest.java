package org.geepawhill.contentment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import javafx.scene.layout.Pane;

import static org.geepawhill.contentment.TestStep.*;

public class StepperTest
{

	private Stepper player;
	Pane canvas;

	@Before
	public void before()
	{
		player = new Stepper();
		canvas = new Pane();
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
		player.load(oneStepSequence, canvas);
		assertEquals(1,player.size());
	}
	
	@Test
	public void loadBeforesAll()
	{
		oneStep.isBefore=false;
		player.load(oneStepSequence, canvas);
		assertTrue(oneStep.isBefore);
	}
	
	@Test
	public void stepForwardDoes()
	{
		player.load(oneStepSequence, canvas);
		player.stepForward(canvas);
		assertEquals(1,player.current());
		assertFalse(oneStep.isBefore);
	}
	
	@Test
	public void stepBackwardDoes()
	{
		player.load(oneStepSequence, canvas);
		player.stepForward(canvas);
		player.stepBackward(canvas);
		assertEquals(0,player.current());
		assertTrue(oneStep.isBefore);
	}
	
	@Test
	public void stepForwardNoopsAtEnd()
	{
		player.load(oneStepSequence, canvas);
		player.stepForward(canvas);
		player.stepForward(canvas);
		assertEquals(1,player.current());
		assertFalse(oneStep.isBefore);
	}
	
	@Test
	public void stepBackwardNoopsAtBeginning()
	{
		player.load(oneStepSequence, canvas);
		player.stepBackward(canvas);
		assertEquals(0,player.current());
		assertTrue(oneStep.isBefore);
	}
	
	@Test
	public void seekNoChangeDoesBefore()
	{
		player.load(oneStepSequence, canvas);
		player.stepForward(canvas);
		player.seek(canvas, 0);
		assertEquals(0,player.current());
		assertTrue(oneStep.isBefore);
	}
	
	@Test
	public void seekForward()
	{
		player.load(twoStepSequence, canvas);
		player.seek(canvas, 1);
		assertEquals(1,player.current());
		assertFalse(oneStep.isBefore);
		assertTrue(twoStep.isBefore);
	}
	
	@Test
	public void seekBackward()
	{
		player.load(twoStepSequence, canvas);
		player.stepForward(canvas);
		player.stepForward(canvas);
		player.seek(canvas, 0);
		assertEquals(0,player.current());
		assertTrue(oneStep.isBefore);
		assertTrue(twoStep.isBefore);
	}
	
	@Test
	public void seekCurrentResets()
	{
		player.load(twoStepSequence, canvas);
		oneStep.isBefore=false;
		player.seek(canvas, 0);
		assertEquals(0,player.current());
		assertTrue(oneStep.isBefore);
		assertTrue(twoStep.isBefore);
	}
	
	@Test
	public void seekBeforeStart()
	{
		player.load(twoStepSequence, canvas);
		player.stepForward(canvas);
		player.stepForward(canvas);
		player.seek(canvas, -20);
		assertEquals(0,player.current());
		assertTrue(oneStep.isBefore);
		assertTrue(twoStep.isBefore);		
	}
	
	@Test
	public void seekAfterEnd()
	{
		player.load(twoStepSequence, canvas);
		player.seek(canvas, 20);
		assertEquals(1,player.current());
		assertFalse(oneStep.isBefore);
		assertFalse(twoStep.isBefore);		
	}
	
}
