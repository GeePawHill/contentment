package org.geepawhill.contentment.newstep;

import static org.junit.Assert.*;

import org.geepawhill.contentment.core.Sequence;
import org.geepawhill.contentment.test.JfxTest;
import org.geepawhill.contentment.test.TestInstant;
import org.junit.Before;
import org.junit.Test;

public class InstantStepTest extends JfxTest
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
		tester.waitForPlay(sequence);
		assertFalse(instant.isBefore);
	}
	
	@Test
	public void afterDoes()
	{
		tester.waitForAfter(sequence);
		assertFalse(instant.isBefore);
	}
	
	@Test
	public void beforeDoes()
	{
		tester.waitForPlay(sequence);
		tester.waitForBefore(sequence);
		assertTrue(instant.isBefore);
	}

}
