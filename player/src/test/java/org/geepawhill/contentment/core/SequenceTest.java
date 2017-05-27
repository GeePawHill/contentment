package org.geepawhill.contentment.core;

import static org.junit.Assert.assertEquals;

import org.geepawhill.contentment.test.TestStep;
import org.junit.Test;

public class SequenceTest
{
	@Test
	public void emptyIs()
	{
		assertEquals(0,new Sequence().size());
	}
	
	@Test
	public void variadicLoads()
	{
		assertEquals(1,new Sequence(new TestStep()).size());
		assertEquals(2,new Sequence(new TestStep(),new TestStep()).size());
	}
	
}
