package org.geepawhill.contentment;

import static org.junit.Assert.*;

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
		styles.get(Style.LineColor);
	}
	
	@Test
	public void setAndGet()
	{
		styles.set(Style.LineColor, Color.RED);
		assertEquals(Color.RED,styles.get(Style.LineColor));
	}
	
	@Test
	public void getAfterPush()
	{
		styles.set(Style.LineColor, Color.RED);
		styles.push();
		assertEquals(Color.RED,styles.get(Style.LineColor));
	}
	
	@Test
	public void getNewAfterNewSetAfterPush()
	{
		styles.set(Style.LineColor, Color.RED);
		styles.push();
		styles.set(Style.LineColor, Color.BLACK);
		assertEquals(Color.BLACK,styles.get(Style.LineColor));
	}
	
	@Test
	public void getOldAfterNewSetAfterPop()
	{
		styles.set(Style.LineColor, Color.RED);
		styles.push();
		styles.set(Style.LineColor, Color.BLACK);
		styles.pop();
		assertEquals(Color.RED,styles.get(Style.LineColor));
	}

	@Test(expected=RuntimeException.class)
	public void tooManyPops()
	{
		styles.pop();
		styles.pop();
	}

}
