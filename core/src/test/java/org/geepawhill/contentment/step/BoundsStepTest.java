package org.geepawhill.contentment.step;

import static org.assertj.core.api.Assertions.assertThat;

import org.geepawhill.contentment.fast.SetBounds;
import org.geepawhill.contentment.geometry.Point;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.test.JavaFxTest;
import org.junit.Before;
import org.junit.Test;

import javafx.scene.shape.Rectangle;

public class BoundsStepTest extends JavaFxTest
{
	private Rectangle rectangle;
	private PointPair result;
	private Phrase sequence;

	@Before
	public void before()
	{
		rectangle = new Rectangle(100d, 300d, 500d, 700d);
		SetBounds step = new SetBounds(rectangle, this::collectBounds);
		sequence = new Phrase().add(step);
	}

	public void collectBounds(PointPair result)
	{
		this.result = result;
	}

	@Test
	public void slowCalls()
	{
		runner.slow(sequence);
		assertThat(result.from).isEqualTo(new Point(100d, 300d));
	}

	@Test
	public void fastCalls()
	{
		runner.fast(sequence);
		assertThat(result.from).isEqualTo(new Point(100d, 300d));
	}
}
