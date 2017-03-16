package org.geepawhill.contentment.core;

import static org.junit.Assert.assertEquals;

import org.geepawhill.contentment.format.Style;
import org.geepawhill.contentment.outline.KvOutline;
import org.geepawhill.contentment.style.Dash;
import org.geepawhill.contentment.style.Typeface;
import org.geepawhill.contentment.style.Opacity;
import org.junit.Test;

public class StyleTest
{
	@Test
	public void dumpFont()
	{
		Style style = Typeface.font("Hand.Large", new javafx.scene.text.Font("Buxton Sketch",60d));
		assertOutline("Font = Hand.Large (Buxton Sketch 60.0)", style);
	}
	
	@Test
	public void dumpOpacity()
	{
		Style style = Opacity.opacity(.3d);
		assertOutline("Opacity =  (0.3)", style);
	}
	
	@Test
	public void dumpDash()
	{
		Style style = Dash.dash("Dash",2d,5d, 7d);
		assertOutline("Dash = Dash (2.0, 5.0, 7.0)", style);
	}

	@Test
	public void dumpDashSolid()
	{
		Style style = Dash.solid();
		assertOutline("Dash = SOLID (SOLID)", style);
	}

	private void assertOutline(String expected, Style style)
	{
		KvOutline output = new KvOutline();
		style.outline(output);
		assertEquals(expected,output.asList().get(0).data.toString());
	}
}
