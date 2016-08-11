package org.geepawhill.contentment.core;

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
		Style red = Style.lineColor(Color.RED);
		styles.set(red);
		assertEquals(red,styles.get(StyleId.LineColor));
	}
	
	@Test
	public void getAfterPush()
	{
		Style red = Style.lineColor(Color.RED);
		styles.set(red);
		styles.push();
		assertEquals(red,styles.get(StyleId.LineColor));
	}
	
	@Test
	public void getNewAfterNewSetAfterPush()
	{
		Style red = Style.lineColor(Color.RED);
		styles.set(red);
		styles.push();
		Style black = Style.lineColor(Color.RED);
		styles.set(black);
		assertEquals(black,styles.get(StyleId.LineColor));
	}
	
	@Test
	public void getOldAfterNewSetAfterPop()
	{
		Style red = Style.lineColor(Color.RED);
		styles.set(red);
		styles.push();
		Style black = Style.lineColor(Color.RED);
		styles.set(black);
		styles.pop();
		assertEquals(red,styles.get(StyleId.LineColor));
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
		Style red = Style.lineColor(Color.RED);
		styles.set(red);
		assertEquals(red,styles.get(StyleId.LineColor));
	}

}
