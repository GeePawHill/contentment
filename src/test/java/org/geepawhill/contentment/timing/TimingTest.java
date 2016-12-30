package org.geepawhill.contentment.timing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.geepawhill.contentment.core.RelativeTiming;
import org.geepawhill.contentment.timing.FixedTiming;
import org.geepawhill.contentment.timing.Timing;
import org.geepawhill.contentment.timing.TimingBuilder;
import org.junit.Before;
import org.junit.Test;

public class TimingTest
{
	Timing relative20;
	Timing relative80;
	Timing absolute20;
	Timing absolute80;
	TimingBuilder builder;

	@Before
	public void before()
	{
		relative20 = new RelativeTiming(20d);
		relative80 = new RelativeTiming(80d);
		absolute20 = new FixedTiming(20d);
		absolute80 = new FixedTiming(80d);
		builder = new TimingBuilder();
	}

	@Test
	public void constructors()
	{
		assertEquals(20d, relative20.getRatio(), .1d);
		assertEquals(80d, relative80.getRatio(), .1d);
		assertEquals(0d, absolute20.getRatio(), .1d);
	}

	@Test(expected = RuntimeException.class)
	public void relativeThrowsIfUnset()
	{
		relative20.getAbsolute();
	}

	@Test(expected = RuntimeException.class)
	public void absoluteThrowsIfReset()
	{
		absolute20.setAbsolute(100d);
	}

	@Test
	public void relativeSetsAbsolute()
	{
		relative20.setAbsolute(1000d);
		assertEquals(1000d, relative20.getAbsolute(), 1d);
	}

	@Test
	public void oneRelativeEatsAllTime()
	{
		assertEquals(300d, builder.build(300d, relative20), 0.1d);
		assertEquals(300d, relative20.getAbsolute(), 0.1d);
	}

	@Test
	public void twoRelativesEatAllTime()
	{
		assertEquals(300d, builder.build(300d, relative20, relative80), 0.1d);
		assertEquals(60d, relative20.getAbsolute(), 0.1d);
		assertEquals(240d, relative80.getAbsolute(), 0.1d);
	}
	
	@Test
	public void absolutesAndRelativesCoexist()
	{
		assertEquals(300d, builder.build(300d, absolute80,absolute20,relative20, relative80), 0.1d);
		assertEquals(40d, relative20.getAbsolute(), 0.1d);
		assertEquals(160d, relative80.getAbsolute(), 0.1d);
	}
	
	@Test
	public void absolutesSumWithZeroTotal()
	{
		assertEquals(100d, builder.build(0d, absolute80,absolute20), 0.1d);
	}


	@Test
	public void throwsIfAbsolutesTooBig()
	{
		try
		{
			builder.build(80d, absolute20, absolute80);
			fail("Did not throw on absolute child overage.");
		}
		catch (RuntimeException e)
		{
			assertEquals(TimingBuilder.ABSOLUTE_OVERRUN, e.getMessage());
		}
	}
	
	@Test
	public void throwsIfRelativesButNoTotal()
	{
		try
		{
			builder.build(0d, relative20, absolute80);
			fail("Did not throw on relatives but no total.");
		}
		catch (RuntimeException e)
		{
			assertEquals(TimingBuilder.RELATIVES_BUT_NO_TOTAL, e.getMessage());
		}
	}

}
