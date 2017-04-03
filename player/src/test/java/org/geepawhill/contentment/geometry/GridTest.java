package org.geepawhill.contentment.geometry;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;

public class GridTest
{
	
	private Grid normal;
	private Grid inset;


	@Before
	public void before()
	{
		normal = new Grid(16,9);
		inset = new Grid(15,8,50d,50d);
	}
	
	@Test
	public void normalValues()
	{
		assertEquals(16,normal.xCells());
		assertEquals(9,normal.yCells());
		assertDoubles(100d,normal.xWidth());
		assertDoubles(100d,normal.yHeight());
	}
	
	@Test
	public void insetValues()
	{
		assertEquals(15,inset.xCells());
		assertEquals(8,inset.yCells());
		assertDoubles(100d,inset.xWidth());
		assertDoubles(100d,inset.yHeight());
	}
	
	@Test
	public void area()
	{
		assertThat(normal.area(0, 0)).isEqualToComparingFieldByField(new PointPair(0d,0d,100d,100d));
	}
	
	@Test
	public void center()
	{
		assertEquals(new Point(50d,50d),normal.center(0,0));
	}


	private void assertDoubles(double expected, double actual)
	{
		assertEquals(actual,expected,0.05d*actual);
	}
}
