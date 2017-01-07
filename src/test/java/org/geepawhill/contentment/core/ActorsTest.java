package org.geepawhill.contentment.core;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.geepawhill.contentment.actor.LabelBox;
import org.junit.Before;
import org.junit.Test;

public class ActorsTest
{
	Actors actors = new Actors();
	private LabelBox box1;
	private LabelBox box2;
	
	@Before
	public void before()
	{
		actors = new Actors();
		box1 = new LabelBox("", 0, 0);
		box2 = new LabelBox("", 0, 0);
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

}
