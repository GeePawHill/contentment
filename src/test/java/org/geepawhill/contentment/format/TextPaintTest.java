package org.geepawhill.contentment.format;

import static org.junit.Assert.*;

import org.junit.Test;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class TextPaintTest
{

	@Test
	public void applies()
	{
		Text text = new Text("Hello");
		new TextPaint(Color.RED,Color.BLUE,.6d).apply(text);
		assertEquals(Color.RED,text.getStroke());
		assertEquals(Color.BLUE,text.getFill());
		assertEquals(.6d,text.getOpacity(),.01d);
	}
	
	@Test(expected = RuntimeException.class)
	public void throwsOnNonSet()
	{
		new TextPaint(Color.RED,Color.BLUE,.6d).apply(new Rectangle());
	}
	
}
