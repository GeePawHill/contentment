package org.geepawhill.contentment.geometry;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import org.geepawhill.contentment.jfx.BezierInterpolator;
import org.junit.Before;
import org.junit.Test;

import javafx.scene.shape.Path;

public class BezierInterpolatorTest
{
	
	@Before
	public void before()
	{
		
	}

	@Test
	public void clear()
	{
		BezierInterpolator interpolator = new BezierInterpolator(new Path());
		interpolator.clear(new Point(100d,100d));
		assertThat(interpolator.segments.size()).isEqualTo(0);
	}

}
