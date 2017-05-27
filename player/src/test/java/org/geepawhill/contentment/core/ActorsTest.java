package org.geepawhill.contentment.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.geepawhill.contentment.actor.Actor;
import org.geepawhill.contentment.actor.Actors;
import org.geepawhill.contentment.actors.Spot;
import org.geepawhill.contentment.outline.KvOutline;
import org.junit.Before;
import org.junit.Test;

public class ActorsTest
{
	Actors actors = new Actors();
	private Spot box1;
	private Spot box2;
	
	@Before
	public void before()
	{
		actors = new Actors();
		box1 = new Spot(0d, 0d);
		box2 = new Spot(100d, 100d);
	}

	@Test
	public void empty()
	{
		assertEquals(0,actors.size());
	}
	
	@Test
	public void add()
	{
		actors.add(box1);
		assertEquals(1,actors.size());
		assertEquals(box1,actors.get(0));
	}
	
	@Test
	public void remove()
	{
		actors.add(box1);
		actors.add(box2);
		actors.remove(box1);
		assertEquals(1,actors.size());
		assertEquals(box2,actors.get(0));
	}
	
	@Test
	public void iterate()
	{
		actors.add(box1);
		actors.add(box2);
		Iterator<Actor> iterator = actors.iterator();
		assertTrue(iterator.hasNext());
		assertEquals(box1,iterator.next());
		assertEquals(box2,iterator.next());
		assertFalse(iterator.hasNext());
	}
	
	@Test
	public void outline()
	{
		actors.add(box1);
		KvOutline output = new KvOutline();
		actors.outline(output);
		assertEquals("Spot_1 = (0.0,0.0)",output.find("Actors.Spot_1").toString());
	}

}
