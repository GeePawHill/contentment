package org.geepawhill.contentment.geometry;

import static org.assertj.core.api.Assertions.within;
import static org.geepawhill.contentment.test.ContentmentAssertions.assertThat;

import org.junit.*;

public class BezierSplitTest
{
	private Point start;
	private Point end;
	private Bezier linear;

	@Before
	public void before()
	{
		start = new Point(0d, 0d);
		end = new Point(100d, 100d);
		linear = new Bezier(start, end);
	}

	@Test
	public void linearHalf()
	{
		BezierSplit split = new BezierSplit(.5d, linear);
		assertThat(split.after.end).isGoodEnough(new Point(100d, 100d), within(0.1d));
		assertThat(split.after.start).isGoodEnough(new Point(50d, 50d), within(0.1d));
	}

	@Test
	public void linearQuarter()
	{
		BezierSplit split = new BezierSplit(.25d, linear);
		assertThat(split.after.end).isGoodEnough(new Point(100d, 100d), within(0.1d));
		assertThat(split.after.start).isGoodEnough(new Point(25d, 25d), within(0.1d));
	}

}
