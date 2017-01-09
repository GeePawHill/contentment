package org.geepawhill.contentment.newstep;

import static org.junit.Assert.*;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.test.StepTest;
import org.geepawhill.contentment.timing.FixedTiming;
import org.junit.Before;
import org.junit.Test;

import javafx.scene.shape.Line;

public class EdgeTest extends StepTest
{

	private Line line;
	private Edge edge;
	
	@Before
	public void before()
	{
		line = new Line();
		runner.actLater((latch) -> { runner.context.canvas.getChildren().add(line); latch.countDown(); });
		edge = new Edge(new FixedTiming(1d),new PointPair(100d,200d,300d,400d), line);
	}

	@Test
	public void playChanges()
	{
		play(edge);
		assertEquals(100d,line.getStartX(),.1d);
		assertEquals(200d,line.getStartY(),.1d);
		assertEquals(300d,line.getEndX(),.1d);
		assertEquals(400d,line.getEndY(),.1d);
	}
}
