package org.geepawhill.contentment;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import javafx.scene.paint.Color;

public class StylesTest
{

	private Styles styles;
	
	@Before
	public void before()
	{
		styles = new Styles();
	}

	@Test(expected=RuntimeException.class)
	public void throwsOnMissing()
	{
		styles.get(StyleId.LineColor);
	}
	
	@Test
	public void setAndGet()
	{
		styles.set(StyleId.LineColor, Color.RED);
		assertEquals(Color.RED,styles.get(StyleId.LineColor).value);
	}
	
	@Test
	public void getAfterPush()
	{
		styles.set(StyleId.LineColor, Color.RED);
		styles.push();
		assertEquals(Color.RED,styles.get(StyleId.LineColor).value);
	}
	
	@Test
	public void getNewAfterNewSetAfterPush()
	{
		styles.set(StyleId.LineColor, Color.RED);
		styles.push();
		styles.set(StyleId.LineColor, Color.BLACK);
		assertEquals(Color.BLACK,styles.get(StyleId.LineColor).value);
	}
	
	@Test
	public void getOldAfterNewSetAfterPop()
	{
		styles.set(StyleId.LineColor, Color.RED);
		styles.push();
		styles.set(StyleId.LineColor, Color.BLACK);
		styles.pop();
		assertEquals(Color.RED,styles.get(StyleId.LineColor).value);
	}

	@Test(expected=RuntimeException.class)
	public void tooManyPops()
	{
		styles.pop();
		styles.pop();
	}
	
	@Test
	public void setWholeStyle()
	{
		Style style = new Style(StyleId.LineColor,Color.RED);
		styles.set(style);
		assertEquals(style,styles.get(StyleId.LineColor));
	}

}
