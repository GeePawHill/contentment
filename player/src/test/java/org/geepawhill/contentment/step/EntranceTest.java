package org.geepawhill.contentment.step;

import org.geepawhill.contentment.actors.Spot;
import org.geepawhill.contentment.test.SequenceTester;
import org.junit.Before;
import org.junit.Test;

public class EntranceTest extends SequenceTester
{

	private Spot spot;
	private Entrance entrance;

	@Before
	public void before()
	{
		spot = new Spot(100d,100d);
		entrance = new Entrance(spot);
	}
	
	@Test
	public void contract()
	{
		assertContractValid(entrance);
	}

}
