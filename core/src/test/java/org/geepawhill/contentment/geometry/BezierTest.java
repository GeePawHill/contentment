package org.geepawhill.contentment.geometry;

import static org.assertj.core.api.Assertions.within;
import static org.geepawhill.contentment.test.PointAssert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class BezierTest
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
	public void constructor()
	{
		PointPair line = new PointPair(linear.start, linear.end);
		assertThat(linear.start).isEqualTo(start);
		assertThat(linear.end).isEqualTo(end);
		assertThat(linear.handle1).isEqualTo(line.along(1d / 3d));
		assertThat(linear.handle2).isEqualTo(line.along(2d / 3d));
	}

	@Test
	public void at()
	{
		assertThat(linear.at(0d)).isEqualTo(start);
		assertThat(linear.at(1d)).isEqualTo(end);
		assertThat(linear.at(.5d)).isGoodEnough(new PointPair(start, end).center(), within(0.1d));
		for (int i = 0; i < 11; i++)
		{
			double fraction = ((double) i) / 10;
			assertThat(linear.at(fraction)).isGoodEnough(new PointPair(0d, 0d, 100d, 100d).along(fraction), within(0.1d));
			assertThat(linear.at(fraction)).isGoodEnough(new PointPair(0d, 0d, 100d, 100d).along(fraction), within(0.1d));
		}
	}

}
