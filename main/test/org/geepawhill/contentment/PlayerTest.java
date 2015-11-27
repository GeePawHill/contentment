package org.geepawhill.contentment;

import static org.junit.Assert.*;

import org.junit.Test;

public class PlayerTest
{
	static class TestStep implements Step
	{
		
	}
	
	@Test
	public void newIsEmpty()
	{
		assertEquals(0,new Player().size());
	}
	
	@Test
	public void newIsBeforeAll()
	{
		assertEquals(-1,new Player().current());
	}
	
	@Test
	public void loadsLoads()
	{
		Player player = new Player();
		player.load(new Sequence(new TestStep()));
		assertEquals(1,player.size());
	}
}
