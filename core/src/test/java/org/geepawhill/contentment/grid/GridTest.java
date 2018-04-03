package org.geepawhill.contentment.grid;

import org.junit.Before;

import org.junit.Test;
import static org.geepawhill.contentment.test.ContentmentAssertions.*;

import org.assertj.core.data.Offset;
import org.geepawhill.contentment.geometry.*;

public class GridTest
{
	private PointPair bounds;
	private Grid grid;

	@Before
	public void before()
	{
		bounds = new PointPair(0, 100, 300, 500);
		grid = new Grid(bounds);
	}

	@Test
	public void gridHasBorders()
	{
		assertThat(grid.top().points()).isEqualTo(bounds.northLine());
		assertThat(grid.left().points()).isEqualTo(bounds.westLine());
		assertThat(grid.bottom().points()).isEqualTo(bounds.southLine());
		assertThat(grid.right().points()).isEqualTo(bounds.eastLine());
	}

	@Test
	public void verticalAndHorizontal()
	{
		assertThat(grid.vertical(50).points()).isEqualTo(bounds.xCenterLine());
		assertThat(grid.vertical(25).points().from.x).isCloseTo(bounds.along(.25).x, Offset.offset(1d));
		assertThat(grid.horizontal(50).points()).isEqualTo(bounds.yCenterLine());
		assertThat(grid.horizontal(25).points().from.y).isCloseTo(bounds.along(.25).y, Offset.offset(1d));
	}

	@Test
	public void point()
	{
		assertThat(grid.point(grid.vertical(50), grid.horizontal(50))).isEqualTo(bounds.center());
		assertThat(grid.point(grid.vertical(33), grid.horizontal(33))).isEqualTo(bounds.along(.33));
	}

	@Test
	public void area()
	{
		assertThat( grid.area(grid.left(), grid.top(), grid.right(), grid.bottom())).isEqualTo(bounds);
		PointPair centerThird = new PointPair(bounds.along(.33), bounds.along(.66));
		assertThat( grid.area(grid.vertical(33), grid.horizontal(33), grid.vertical(66), grid.horizontal(66))).isEqualTo(centerThird);
	}
}
