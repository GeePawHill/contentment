package org.geepawhill.contentment.core;

import static org.junit.Assert.assertEquals;

import org.geepawhill.contentment.outline.KvOutline;
import org.geepawhill.contentment.style.Dash;
import org.geepawhill.contentment.style.Font;
import org.geepawhill.contentment.style.Opacity;
import org.geepawhill.contentment.style.PenWidth;
import org.junit.Test;

public class StyleTest
{
	@Test
	public void dumpFont()
	{
		Style style = Font.font(new javafx.scene.text.Font("Buxton Sketch",60d));
		assertOutline("Font = Buxton Sketch 60.0", style);
	}
	
	@Test
	public void dumpOpacity()
	{
		Style style = Opacity.opacity(.3d);
		assertOutline("Opacity = 0.3", style);
	}
	
	@Test
	public void dumpPenWidth()
	{
		Style style = PenWidth.penWidth(7d);
		assertOutline("PenWidth = 7.0", style);
	}

	@Test
	public void dumpDash()
	{
		Style style = Dash.dash(2d,5d,7d);
		assertOutline("Dash = 2.0, 5.0, 7.0", style);
	}

	@Test
	public void dumpDashSolid()
	{
		Style style = Dash.solid();
		assertOutline("Dash = SOLID", style);
	}

	private void assertOutline(String expected, Style style)
	{
		KvOutline output = new KvOutline();
		style.outline(output);
		assertEquals(expected,output.asList().get(0).data.toString());
	}
}
