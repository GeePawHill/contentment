package org.geepawhill.contentment.outline;

import static org.junit.Assert.*;

import org.geepawhill.contentment.outline.KeyValue;
import org.geepawhill.contentment.outline.KvOutline;
import org.junit.Before;
import org.junit.Test;

public class KvOutlineTest
{

	
	private KvOutline outline;
	
	@Before
	public void before()
	{
		outline = new KvOutline();
	}

	@Test
	public void findAtZero()
	{
		outline.append("Parent");
		outline.append("Parent2");
		KeyValue pair = outline.find("Parent");
		assertEquals("Parent",pair.toString());
		assertEquals("Parent2",outline.find("Parent2").toString());
	}

	@Test
	public void childFind()
	{
		outline.append("Parent");
		outline.indent();
		outline.append("Wrong");
		outline.indent();
		outline.append("GrandChild","Stuff");
		outline.dedent();
		outline.append("Child","Value");
		KeyValue pair = outline.find("Parent.Child");
		assertEquals("Child = Value",pair.toString());
	}

}
