package org.geepawhill.contentment.format;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.geepawhill.contentment.style.Frames;
import org.junit.Before;
import org.junit.Test;

public class FormatTest
{
	
	private Format base;
	private Style style;
	private String key;

	@Before
	public void before()
	{
		style = Frames.unspecified();
		base = new Format("Base");
		key = style.key();
	}
	
	@Test(expected=MissingFormatException.class)
	public void throwsOnMissingStyle()
	{
		base.require(key);
	}

	@Test
	public void overrides()
	{
		assertNull(base.find(key));
		base.put(style);
		assertEquals(style,base.find(Frames.KEY));
	}
	
	@Test
	public void hasBase()
	{
		Format derived = new Format("Derived",base);
		assertEquals(base,derived.base);
	}
	
	@Test
	public void findsInBase()
	{
		base.put(style);
		Format derived = new Format("Derived",base);
		assertEquals(style,derived.find(key));
	}
}
