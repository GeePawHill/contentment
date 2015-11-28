package org.geepawhill.contentment;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class PlayerTest
{
	private Sequence oneStepSequence;
	private Player player;
	private TestStep oneStep;
	private TestStep twoStep;
	private Sequence twoStepSequence;

	static class TestStep implements Step
	{
		public boolean isBefore;
		public TestStep()
		{
			isBefore=true;
		}

		@Override
		public void jumpAfter()
		{
			isBefore=false;
		}

		@Override
		public void jumpBefore()
		{
			isBefore=true;
		}
		
	}
	
	@Before
	public void before()
	{
		player = new Player();
		oneStep = new TestStep();
		twoStep = new TestStep();
		oneStepSequence = new Sequence(oneStep);
		twoStepSequence = new Sequence(oneStep,twoStep);
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
		player.load(oneStepSequence);
		assertEquals(1,player.size());
	}
	
	@Test
	public void loadBeforesAll()
	{
		oneStep.isBefore=false;
		player.load(oneStepSequence);
		assertTrue(oneStep.isBefore);
	}
	
	@Test
	public void stepForwardDoes()
	{
		player.load(oneStepSequence);
		player.stepForward();
		assertEquals(1,player.current());
		assertFalse(oneStep.isBefore);
	}
	
	@Test
	public void stepBackwardDoes()
	{
		player.load(oneStepSequence);
		player.stepForward();
		player.stepBackward();
		assertEquals(0,player.current());
		assertTrue(oneStep.isBefore);
	}
	
	@Test
	public void stepForwardNoopsAtEnd()
	{
		player.load(oneStepSequence);
		player.stepForward();
		player.stepForward();
		assertEquals(1,player.current());
		assertFalse(oneStep.isBefore);
	}
	
	@Test
	public void stepBackwardNoopsAtBeginning()
	{
		player.load(oneStepSequence);
		player.stepBackward();
		assertEquals(0,player.current());
		assertTrue(oneStep.isBefore);
	}
	
	@Ignore
	@Test
	public void seekNoChangeDoesBefore()
	{
		player.load(oneStepSequence);
		player.stepForward();
		player.seek(0);
		assertEquals(0,player.current());
		assertTrue(oneStep.isBefore);
	}
	
	@Test
	public void seekForward()
	{
		player.load(twoStepSequence);
		player.seek(1);
		assertEquals(1,player.current());
		assertFalse(oneStep.isBefore);
		assertTrue(twoStep.isBefore);
	}
	
	@Test
	public void seekBackward()
	{
		player.load(twoStepSequence);
		player.stepForward();
		player.stepForward();
		player.seek(0);
		assertEquals(0,player.current());
		assertTrue(oneStep.isBefore);
		assertTrue(twoStep.isBefore);
	}
	
	@Test
	public void seekCurrentResets()
	{
		player.load(twoStepSequence);
		oneStep.isBefore=false;
		player.seek(0);
		assertEquals(0,player.current());
		assertTrue(oneStep.isBefore);
		assertTrue(twoStep.isBefore);
	}
	
	@Test
	public void seekBeforeStart()
	{
		player.load(twoStepSequence);
		player.stepForward();
		player.stepForward();
		player.seek(-20);
		assertEquals(0,player.current());
		assertTrue(oneStep.isBefore);
		assertTrue(twoStep.isBefore);		
	}
	
	@Test
	public void seekAfterEnd()
	{
		player.load(twoStepSequence);
		player.seek(20);
		assertEquals(1,player.current());
		assertFalse(oneStep.isBefore);
		assertFalse(twoStep.isBefore);		
	}
	
}
