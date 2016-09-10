package org.geepawhill.contentment.core;

import static org.junit.Assert.assertEquals;

import org.geepawhill.contentment.style.Style;
import org.geepawhill.contentment.style.StyleId;
import org.geepawhill.contentment.style.Styles;
import org.junit.Before;
import org.junit.Test;

import javafx.scene.paint.Color;

public class StylesTest
{

	private Styles styles;
	private Style redLine;
	private Style blackLine;
	
	@Before
	public void before()
	{
		styles = new Styles();
		redLine = Style.lineColor(Color.RED);
		blackLine = Style.lineColor(Color.BLACK);
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
	public void getAfterPush()
	{
		styles.set(redLine);
		styles.push();
		assertEquals(redLine,styles.get(StyleId.LineColor));
	}
	
	@Test
	public void getNewAfterNewSetAfterPush()
	{
		styles.set(redLine);
		styles.push();
		styles.set(blackLine);
		assertEquals(blackLine,styles.get(StyleId.LineColor));
	}
	
	@Test
	public void getOldAfterNewSetAfterPop()
	{
		styles.set(redLine);
		styles.push();
		styles.set(blackLine);
		styles.pop();
		assertEquals(redLine,styles.get(StyleId.LineColor));
	}

	@Test(expected=RuntimeException.class)
	public void tooManyPops()
	{
		styles.pop();
		styles.pop();
	}
	
}
