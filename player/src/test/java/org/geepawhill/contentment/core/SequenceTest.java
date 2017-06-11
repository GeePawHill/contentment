package org.geepawhill.contentment.core;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SequenceTest
{
	@Test
	public void emptyIs()
	{
		assertEquals(0,new Sequence().size());
	}
	
}
