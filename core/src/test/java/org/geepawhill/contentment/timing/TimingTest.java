package org.geepawhill.contentment.timing;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

public class TimingTest
{
	Timing relative20;
	Timing relative80;
	Timing absolute20;
	Timing absolute80;
	Scheduler scheduler;

	@Before
	public void before()
	{
		relative20 = Timing.weighted(20d);
		relative80 = Timing.weighted(80d);
		absolute20 = Timing.ms(20d);
		absolute80 = Timing.ms(80d);
		scheduler = new Scheduler();
	}

	@Test
	public void constructors()
	{
		assertWeighted(relative20,20d);
		assertWeighted(relative80,80d);
		assertFixed(absolute80, 80d);
	}


	@Test(expected = RuntimeException.class)
	public void relativeThrowsIfUnset()
	{
		relative20.fixed();
	}

	@Test(expected = RuntimeException.class)
	public void absoluteThrowsIfReset()
	{
		absolute20.fix(100d);
	}

	@Test
	public void relativeSetsAbsolute()
	{
		relative20.fix(1000d);
		assertEquals(1000d, relative20.fixed(), 1d);
	}

	@Test
	public void oneRelativeEatsAllTime()
	{
		assertEquals(300d, scheduler.schedule(300d, relative20), 0.1d);
		assertEquals(300d, relative20.fixed(), 0.1d);
	}

	@Test
	public void twoRelativesEatAllTime()
	{
		assertEquals(300d, scheduler.schedule(300d, relative20, relative80), 0.1d);
		assertEquals(60d, relative20.fixed(), 0.1d);
		assertEquals(240d, relative80.fixed(), 0.1d);
	}
	
	@Test
	public void absolutesAndRelativesCoexist()
	{
		assertEquals(300d, scheduler.schedule(300d, absolute80,absolute20,relative20, relative80), 0.1d);
		assertEquals(40d, relative20.fixed(), 0.1d);
		assertEquals(160d, relative80.fixed(), 0.1d);
	}
	
	@Test
	public void absolutesSumWithZeroTotal()
	{
		assertEquals(100d, scheduler.schedule(0d, absolute80,absolute20), 0.1d);
	}


	@Test
	public void throwsIfAbsolutesTooBig()
	{
		try
		{
			scheduler.schedule(80d, absolute20, absolute80);
			fail("Did not throw on absolute child overage.");
		}
		catch (RuntimeException e)
		{
			assertEquals(Scheduler.ABSOLUTE_OVERRUN, e.getMessage());
		}
	}
	
	@Test
	public void throwsIfRelativesButNoTotal()
	{
		try
		{
			scheduler.schedule(0d, relative20, absolute80);
			fail("Did not throw on relatives but no total.");
		}
		catch (RuntimeException e)
		{
			assertEquals(Scheduler.RELATIVES_BUT_NO_TOTAL, e.getMessage());
		}
	}
	
	@Test
	public void relativesGetMinimumTime()
	{
		scheduler.schedule(80d, absolute80,relative20);
		assertThat(relative20.fixed()).isEqualTo(.1d);
	}
	
	private void assertFixed(Timing timing, double expected)
	{
		assertThat(timing.isWeighted()).isFalse();
		assertThat(timing.fixed()).isCloseTo(expected, within(0.1d));
	}

	private void assertWeighted(Timing timing, double weight)
	{
		assertThat(timing.isWeighted()).isTrue();
		assertThat(timing.weight()).isCloseTo(weight, within(0.1d));
	}


}
