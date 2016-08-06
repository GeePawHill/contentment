package org.geepawhill.contentment.core;

import static org.junit.Assert.*;

import org.geepawhill.contentment.core.Style;
import org.geepawhill.contentment.core.StyleId;
import org.junit.Test;

import javafx.scene.paint.Color;

public class StyleTest
{

	@Test
	public void constructor()
	{
		Style style = new Style(StyleId.LineColor,Color.RED);
		assertEquals(StyleId.LineColor,style.id);
		assertEquals(Color.RED,style.value);
	}

}
