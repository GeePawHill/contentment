package org.geepawhill.contentment.newstep;

import static org.junit.Assert.assertEquals;

import org.geepawhill.contentment.core.Sequence;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.test.StepTest;
import org.geepawhill.contentment.test.TestActor;
import org.geepawhill.contentment.timing.FixedTiming;
import org.junit.Before;
import org.junit.Test;

import javafx.scene.shape.Line;

public class EdgeTest extends StepTest
{

	private Line line;
	private Edge edge;
	private TestActor actor;
	private Sequence sequence;
	
	@Before
	public void before()
	{
		line = new Line();
		runner.actLater((latch) -> {
			sequence = new Sequence();
			actor = new TestActor(line);
			edge = new Edge(new FixedTiming(1d),new PointPair(100d,200d,300d,400d), line);
			sequence.add(new Entrance(actor));
			sequence.add(edge);
			latch.countDown(); 
			}
		);
	}

	@Test
	public void playChanges()
	{
		play(sequence);
		assertEquals(100d,line.getStartX(),.1d);
		assertEquals(200d,line.getStartY(),.1d);
		assertEquals(300d,line.getEndX(),.1d);
		assertEquals(400d,line.getEndY(),.1d);
	}
	
	@Test
	public void contract()
	{
		assertContractValid(sequence);
	}
}
