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

}
