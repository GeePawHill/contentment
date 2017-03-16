package org.geepawhill.contentment.core;

import static org.junit.Assert.assertEquals;

import org.geepawhill.contentment.format.Style;
import org.geepawhill.contentment.format.Styles;
import org.geepawhill.contentment.format.StylesMemo;
import org.geepawhill.contentment.outline.KvOutline;
import org.geepawhill.contentment.style.ShapePen;
import org.geepawhill.contentment.test.ContextOutline;
import org.junit.Before;
import org.junit.Test;

public class StylesTest
{
	private Styles styles;
	private Style first;
	private Style second;
	
	@Before
	public void before()
	{
		styles = new Styles();
		first = ShapePen.first();
		second = ShapePen.second();
	}
	
	@Test(expected=RuntimeException.class)
	public void throwsOnMissing()
	{
		styles.get(ShapePen.KIND);
	}
	
	@Test
	public void setAndGet()
	{
		styles.set(first);
		assertEquals(first,styles.get(ShapePen.KIND));
	}
	
	@Test
	public void setReturnsOld()
	{
		styles.set(first);
		assertEquals(first,styles.set(second));
	}

	public void getAllSetAll()
	{
		styles.set(first);
		StylesMemo previous = styles.getAll();
		styles.set(second);
		assertEquals(second,styles.get(ShapePen.KIND));
		styles.setAll(previous);
		assertEquals(first,styles.get(ShapePen.KIND));
	}

	@Test
	public void outline()
	{
		KvOutline tree = new KvOutline();
		styles.set(first);
		styles.outline(tree);
		ContextOutline outline = new ContextOutline(tree);
		outline.assertKey("Styles."+ShapePen.KIND);
	}
	
}
