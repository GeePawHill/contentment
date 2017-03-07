package org.geepawhill.contentment.format;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import javafx.scene.paint.Color;

public class FormatTest
{
	
	private Format base;
	private TextPaint style;

	@Before
	public void before()
	{
		style = new TextPaint(Color.RED,Color.BLUE,.5d);
		base = new Format("Base");
	}

	@Test
	public void overrides()
	{
		assertNull(base.findStyle("TextPaint"));
		base.override("TextPaint",style);
		assertEquals(style,base.findStyle("TextPaint"));
	}
	
	@Test(expected = RuntimeException.class)
	public void missingStyle()
	{
		base.style("TextPaint");
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
		base.override("TextPaint",style);
		Format derived = new Format("Derived",base);
		assertEquals(style,derived.findStyle("TextPaint"));
	}


}
