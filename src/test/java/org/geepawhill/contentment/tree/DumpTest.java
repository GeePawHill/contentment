package org.geepawhill.contentment.tree;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DumpTest
{

	
	private Dump dump;
	
	@Before
	public void before()
	{
		dump = new Dump();
	}

	@Test
	public void findAtZero()
	{
		dump.append("Parent");
		dump.append("Parent2");
		KeyValue pair = dump.find("Parent");
		assertEquals("Parent",pair.toString());
		assertEquals("Parent2",dump.find("Parent2").toString());
	}

	@Test
	public void childFind()
	{
		dump.append("Parent");
		dump.indent();
		dump.append("Wrong");
		dump.indent();
		dump.append("GrandChild","Stuff");
		dump.dedent();
		dump.append("Child","Value");
		KeyValue pair = dump.find("Parent.Child");
		assertEquals("Child = Value",pair.toString());
	}

}
