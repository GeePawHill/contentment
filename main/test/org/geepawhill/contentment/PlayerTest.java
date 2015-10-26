package org.geepawhill.contentment;

import static org.junit.Assert.*;

import org.junit.Test;

public class PlayerTest
{

	@Test
	public void startsBefore()
	{
		assertEquals(Player.Status.Before,new Player().status());
	}

}
