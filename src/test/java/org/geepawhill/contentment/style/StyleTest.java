package org.geepawhill.contentment.style;

import static org.junit.Assert.assertEquals;

import org.geepawhill.contentment.core.Style;
import org.geepawhill.contentment.outline.KvOutline;
import org.junit.Test;

public class StyleTest
{

	@Test
	public void dumpFont()
	{
		Style style = Font.font(new javafx.scene.text.Font("Buxton Sketch",60d));
		assertDump("Font = Buxton Sketch 60.0", style);
	}
	
	@Test
	public void dumpLineColor()
	{
		Style style = LineColor.black();
		assertDump("LineColor = BLACK (0x000000ff)", style);
	}

	@Test
	public void dumpOpacity()
	{
		Style style = Opacity.opacity(.3d);
		assertDump("Opacity = 0.3", style);
	}
	
	@Test
	public void dumpPenWidth()
	{
		Style style = PenWidth.penWidth(7d);
		assertDump("PenWidth = 7.0", style);
	}

	@Test
	public void dumpDash()
	{
		Style style = Dash.dash(2d,5d,7d);
		assertDump("Dash = 2.0, 5.0, 7.0", style);
	}

	@Test
	public void dumpDashSolid()
	{
		Style style = Dash.solid();
		assertDump("Dash = SOLID", style);
	}

	private void assertDump(String expected, Style style)
	{
		KvOutline output = new KvOutline();
		style.outline(output);
		assertEquals(expected,output.asList().get(0).data.toString());
	}

}
