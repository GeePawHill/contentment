package org.geepawhill.contentment.timing;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.*;

public class TimingTest
{
	Timing weighted20;
	Timing wieghted80;
	Timing fixed20;
	Timing fixed80;
	Scheduler scheduler;

	@Before
	public void before()
	{
		weighted20 = Timing.weighted(20d);
		wieghted80 = Timing.weighted(80d);
		fixed20 = Timing.ms(20d);
		fixed80 = Timing.ms(80d);
		scheduler = new Scheduler();
	}

	@Test
	public void constructors()
	{
		assertWeighted(weighted20, 20d);
		assertFixed(fixed80, 80d);
	}

	@Test(expected = RuntimeException.class)
	public void weightedThrowsOnGetIfUnset()
	{
		weighted20.ms();
	}

	@Test(expected = RuntimeException.class)
	public void fixedThrowsIfSetTwice()
	{
		fixed20.fix(100d);
	}

	@Test
	public void weightedCanFix()
	{
		weighted20.fix(1000d);
		assertEquals(1000d, weighted20.ms(), 1d);
	}

	@Test
	public void oneWeightedEatsAllTime()
	{
		assertEquals(300d, scheduler.schedule(300d, weighted20), 0.1d);
		assertEquals(300d, weighted20.ms(), 0.1d);
	}

	@Test
	public void twoWeightedEatAllTime()
	{
		assertEquals(300d, scheduler.schedule(300d, weighted20, wieghted80), 0.1d);
		assertEquals(60d, weighted20.ms(), 0.1d);
		assertEquals(240d, wieghted80.ms(), 0.1d);
	}

	@Test
	public void fixedAndWeightedCoexist()
	{
		assertEquals(300d, scheduler.schedule(300d, fixed80, fixed20, weighted20, wieghted80), 0.1d);
		assertEquals(40d, weighted20.ms(), 0.1d);
		assertEquals(160d, wieghted80.ms(), 0.1d);
	}

	@Test
	public void fixedWithZeroJustSums()
	{
		assertEquals(100d, scheduler.schedule(0d, fixed80, fixed20), 0.1d);
	}

	@Test
	public void throwsIfRelativesButZeroTotal()
	{
		try
		{
			scheduler.schedule(0d, weighted20, fixed80);
			fail("Did not throw on relatives but no total.");
		}
		catch (RuntimeException e)
		{
			assertEquals(Scheduler.RELATIVES_BUT_NO_TOTAL, e.getMessage());
		}
	}

	@Test
	public void weightedsGetMinimumTime()
	{
		scheduler.schedule(80d, fixed80, weighted20);
		assertThat(weighted20.ms()).isEqualTo(.1d);
	}

	private void assertFixed(Timing timing, double expected)
	{
		assertThat(timing.isWeighted()).isFalse();
		assertThat(timing.ms()).isCloseTo(expected, within(0.1d));
	}

	private void assertWeighted(Timing timing, double weight)
	{
		assertThat(timing.isWeighted()).isTrue();
		assertThat(timing.weight()).isCloseTo(weight, within(0.1d));
	}
}
