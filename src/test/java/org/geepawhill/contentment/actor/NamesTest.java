package org.geepawhill.contentment.actor;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class NamesTest
{
	
	@Before
	public void before()
	{
		Names.reset();
	}

	@Test
	public void sequences()
	{
		assertEquals("Test_1",Names.make("Test"));
		assertEquals("Test_2",Names.make("Test"));
	}

	@Test
	public void rootCounts()
	{
		assertEquals("Test_1",Names.make("Test"));
		assertEquals("Something_1",Names.make("Something"));
	}

}
