package org.geepawhill.contentment.core;

import static org.junit.Assert.assertEquals;

import org.geepawhill.contentment.newstep.InstantStep;
import org.geepawhill.contentment.test.TestInstant;
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
	
	@Test
	public void add()
	{
		Sequence sequence = new Sequence();
		sequence.add(new InstantStep(new TestInstant()));
		assertEquals(1,sequence.size());
	}

	@Test
	public void addInstant()
	{
		Sequence sequence = new Sequence();
		sequence.add(new TestInstant());
		assertEquals(1,sequence.size());
	}

}
