package org.geepawhill.contentment.test;

import org.junit.Test;
import static org.geepawhill.contentment.test.ContentmentAssertions.*;

import org.geepawhill.contentment.geometry.*;


public class ContentmentAssertionsTest
{
	@Test
	public void points()
	{
		Point one = new Point(100,100);
		Point two = new Point(100,100);
		assertThat(one).isEqualTo(two);
	}
	
	@Test
	public void withinOne()
	{
		Point one = new Point(100,100);
		Point two = new Point(100,100.4);
		assertThat(one).isEqualTo(two);
	}
	
	@Test
	public void pairs()
	{
		PointPair one = new PointPair(0,0,100,100);
		PointPair two = new PointPair(0,0,100,100);
		assertThat(one).isEqualTo(two);
	}
}
