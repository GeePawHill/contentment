package org.geepawhill.contentment.step;

import org.geepawhill.contentment.actors.Spot;
import org.junit.Before;

public class EntranceTest
{

	private Spot spot;
	private Entrance entrance;

	@Before
	public void before()
	{
		spot = new Spot(100d,100d);
		entrance = new Entrance(spot);
	}
	
}
