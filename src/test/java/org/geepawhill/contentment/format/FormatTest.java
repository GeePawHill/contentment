package org.geepawhill.contentment.format;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.geepawhill.contentment.style.Opacity;
import org.junit.Before;
import org.junit.Test;

import javafx.scene.paint.Color;

public class FormatTest
{
	
	private Format base;
	private Style style;
	private String key;

	@Before
	public void before()
	{
		style = Opacity.opacity(.5d);
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
		base.override(style);
		assertEquals(style,base.find(Opacity.KEY));
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
		base.override(style);
		Format derived = new Format("Derived",base);
		assertEquals(style,derived.find(key));
	}


}
