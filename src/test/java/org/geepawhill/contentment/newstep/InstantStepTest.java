package org.geepawhill.contentment.newstep;

import static org.junit.Assert.*;

import org.geepawhill.contentment.core.Sequence;
import org.geepawhill.contentment.test.StepTest;
import org.geepawhill.contentment.test.TestInstant;
import org.junit.Before;
import org.junit.Test;

public class InstantStepTest extends StepTest
{

	private Sequence sequence;
	private TestInstant instant;
	
	@Before
	public void before()
	{
		sequence = new Sequence();
		instant = new TestInstant();
		sequence.add(instant);
	}

	@Test
	public void playDoes()
	{
		runner.waitForPlay(sequence);
		assertFalse(instant.isBefore);
	}
	
	@Test
	public void afterDoes()
	{
		runner.waitForAfter(sequence);
		assertFalse(instant.isBefore);
	}
	
	@Test
	public void beforeDoes()
	{
		runner.waitForPlay(sequence);
		runner.waitForBefore(sequence);
		assertTrue(instant.isBefore);
	}

}
