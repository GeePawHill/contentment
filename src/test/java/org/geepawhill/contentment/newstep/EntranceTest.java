package org.geepawhill.contentment.newstep;

import org.geepawhill.contentment.actor.Spot;
import org.geepawhill.contentment.test.ContextOutline;
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
	public void adds()
	{
		ContextOutline outline = play(entrance);
		assertKey(outline,"Actors.Spot_1","(100.0,100.0)");
	}

	@Test
	public void contract()
	{
		assertContractValid(entrance);
	}

}
