package org.geepawhill.contentment.geometry;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

import org.geepawhill.contentment.jfx.BezierInterpolator;
import org.junit.Before;
import org.junit.Test;

import javafx.scene.shape.Path;

public class BezierInterpolatorTest
{
	
	private Point[] controls;
	private BezierInterpolator interpolator;

	@Before
	public void before()
	{
		controls = new Point[] {
				new Point(1000d,1000d),
				new Point(1300d,1000d),
				new Point(1300d,1300d),
				new Point(1000d,1300d)
		};
		interpolator = new BezierInterpolator(new Path());
	}

	@Test
	public void clear()
	{
		interpolator.clear(controls[0]);
		assertThat(interpolator.segments.size()).isEqualTo(0);
	}
	
	@Test
	public void oneFewerStep()
	{
		interpolator.clear(controls[0]);
		interpolator.addCurve(controls, 100);
		assertThat(interpolator.segments.size()).isEqualTo(99);
	}
	
	@Test
	public void startAndEnd()
	{
		interpolator.clear(controls[0]);
		interpolator.addCurve(controls, 10);
		Point lastPoint = interpolator.segments.get(interpolator.segments.size()-1).move;
		assertThat(lastPoint.x).isCloseTo(controls[3].x, within(0.1));
		assertThat(lastPoint.y).isCloseTo(controls[3].y, within(0.1));
		
	}

}
