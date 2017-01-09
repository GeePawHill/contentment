package org.geepawhill.contentment.core;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.geepawhill.contentment.core.Style;
import org.geepawhill.contentment.core.StyleId;
import org.geepawhill.contentment.core.Styles;
import org.geepawhill.contentment.core.StylesMemo;
import org.geepawhill.contentment.outline.KeyValue;
import org.geepawhill.contentment.outline.KvOutline;
import org.geepawhill.contentment.outline.Line;
import org.geepawhill.contentment.style.LineColor;
import org.junit.Before;
import org.junit.Test;

public class StylesTest
{
	private Styles styles;
	private Style redLine;
	private Style blackLine;
	
	@Before
	public void before()
	{
		styles = new Styles();
		redLine = LineColor.red();
		blackLine = LineColor.black();
	}
	
	@Test(expected=RuntimeException.class)
	public void throwsOnMissing()
	{
		styles.get(StyleId.LineColor);
	}
	
	@Test
	public void setAndGet()
	{
		styles.set(redLine);
		assertEquals(redLine,styles.get(StyleId.LineColor));
	}
	
	@Test
	public void setReturnsOld()
	{
		styles.set(redLine);
		assertEquals(redLine,styles.set(blackLine));
	}

	public void getAllSetAll()
	{
		styles.set(redLine);
		StylesMemo previous = styles.getAll();
		styles.set(blackLine);
		assertEquals(blackLine,styles.get(StyleId.LineColor));
		styles.setAll(previous);
		assertEquals(redLine,styles.get(StyleId.LineColor));
	}

	@Test
	public void outline()
	{
		KvOutline tree = new KvOutline();
		styles.set(redLine);
		styles.outline(tree);
		List<Line<KeyValue>> output = tree.asList();
		assertEquals(2,output.size());
		assertEquals("Styles",output.get(0).data.getKey());
		assertEquals("LineColor = RED (0xff0000ff)",output.get(1).data.toString());
	}
	
}
