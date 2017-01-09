package org.geepawhill.contentment.step;

import org.geepawhill.contentment.actor.Spot;
import org.geepawhill.contentment.actor.arrow.Arrow;
import org.geepawhill.contentment.core.Sequence;
import org.geepawhill.contentment.test.StepTest;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class ArrowSketchTest extends StepTest
{

	private Arrow arrow;
	private Spot fromSpot;
	private Spot toSpot;

	@Before
	public void before() throws Exception
	{
		fromSpot = new Spot(100d,100d);
		after(new Sequence(fromSpot.place()));
		toSpot = new Spot(200d,200d);
		after(new Sequence(toSpot.place()));
		arrow = new Arrow(fromSpot,true,toSpot,true);
	}
	
	@Test
	public void contract() throws Exception
	{
		assertContractValid(arrow.sketch(1d));
	}
}
