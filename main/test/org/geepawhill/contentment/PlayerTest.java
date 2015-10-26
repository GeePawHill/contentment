package org.geepawhill.contentment;

import static org.junit.Assert.*;
import static org.geepawhill.contentment.PlayState.*;

import org.junit.Before;
import org.junit.Test;

public class PlayerTest
{

	private Player player;
	
	
	@Before
	public void before()
	{
		player = new Player();
	}

	@Test
	public void startsBefore()
	{
		assertEquals(Before,player.status());
	}
	
	@Test
	public void emptyPlaysToAfter()
	{
		player.play();
		assertEquals(After,player.status());
	}
	
	@Test
	public void resetResets()
	{
		player.play();
		player.reset();
		assertEquals(Before,player.status());
	}

}
