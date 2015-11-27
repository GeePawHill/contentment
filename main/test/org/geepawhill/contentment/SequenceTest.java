package org.geepawhill.contentment;

import static org.junit.Assert.*;

import org.junit.Test;

public class SequenceTest
{
	class TestStep implements Step
	{
		
	}

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
