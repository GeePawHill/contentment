package org.geepawhill.contentment.grid;

import org.junit.Before;

import org.junit.Test;
import static org.geepawhill.contentment.test.ContentmentAssertions.*;

import org.geepawhill.contentment.geometry.*;

public class GridTest
{
	private PointPair bounds;
	private Grid grid;

	@Before
	public void before()
	{
		bounds = new PointPair(0,100,300,500);
		grid = new Grid(bounds);
	}
	
	@Test
	public void gridHasBorders()
	{
		assertThat( grid.top().points()).isEqualTo(bounds.northLine());
		assertThat( grid.left().points()).isEqualTo(bounds.westLine());
		assertThat( grid.bottom().points()).isEqualTo(bounds.southLine());
		assertThat( grid.right().points()).isEqualTo(bounds.eastLine());
	}
	
	@Test
	public void verticalAndHorizontal()
	{
		assertThat( grid.vertical(50).points()).isEqualTo(bounds.xCenterLine());
		assertThat( grid.horizontal(50).points()).isEqualTo(bounds.yCenterLine());
	}
	
	@Test
	public void point()
	{
		assertThat( grid.point(grid.horizontal(50),grid.vertical(50))).isEqualTo(bounds.center());
	}

}
