package org.geepawhill.contentment.step;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class SlideFormatterTest
{
	
	private SlideFormatter formatter;
	private String[] samples;
	
	@Before
	public void before()
	{ 
		formatter = new SlideFormatter();
		samples = new String[] {
				"Line",
				"+Line",
				"++Line",
				"+++Line",
				"=Line"
		};
	}

	@Test
	public void oneTextPerLine()
	{
		assertEquals(1,formatter.layout("a line").size());
		assertEquals(2,formatter.layout("two lines","like so").size());
	}
	
	@Test
	public void markupStripped()
	{
		for(SlideFormat item : formatter.layout(samples))
		{
			assertEquals("Line",item.text.getText());
		}
	}
	
	@Test
	public void titleAttributes()
	{
		SlideFormat format = formatter.layout("Line").get(0);
		assertEquals(new Font("Buxton Sketch",100d),format.text.getFont());
		assertEquals(Color.YELLOW,format.text.getStroke());
		assertEquals(Color.YELLOW,format.text.getFill());
	}
	
	@Test
	public void oneAttributes()
	{
		SlideFormat format = formatter.layout("+Line").get(0);
		assertEquals(new Font("Buxton Sketch",80d),format.text.getFont());
		assertEquals(Color.YELLOWGREEN,format.text.getStroke());
		assertEquals(Color.YELLOWGREEN,format.text.getFill());
	}
	
	@Test
	public void twoAttributes()
	{
		SlideFormat format = formatter.layout("++Line").get(0);
		assertEquals(new Font("Buxton Sketch",60d),format.text.getFont());
		assertEquals(Color.BLUE,format.text.getStroke());
		assertEquals(Color.BLUE,format.text.getFill());
	}
	
	@Test
	public void threeAttributes()
	{
		SlideFormat format = formatter.layout("+++Line").get(0);
		assertEquals(new Font("Buxton Sketch",50d),format.text.getFont());
		assertEquals(Color.RED,format.text.getStroke());
		assertEquals(Color.RED,format.text.getFill());
	}
	
	@Test
	public void centerAttributes()
	{
		SlideFormat format = formatter.layout("=Line").get(0);
		assertEquals(new Font("Buxton Sketch",100d),format.text.getFont());
		assertEquals(Color.YELLOW,format.text.getStroke());
		assertEquals(Color.YELLOW,format.text.getFill());
	}
	
	@Test
	public void verticalStack()
	{
		List<SlideFormat> formats = formatter.layout(samples);
		double lastY=SlideFormatter.VMARGIN;
		for(SlideFormat format : formats)
		{
			assertEquals(lastY,format.text.getY(),0.5d);
			lastY = format.text.getBoundsInParent().getMaxY();
		}
	}
	
	@Test
	public void alignments()
	{
		List<SlideFormat> formats = formatter.layout(samples);
		assertEquals(SlideFormat.Layout.LEFT,formats.get(0).layout);
		assertEquals(SlideFormat.Layout.RIGHT,formats.get(1).layout);
		assertEquals(SlideFormat.Layout.LEFT,formats.get(2).layout);
		assertEquals(SlideFormat.Layout.INDENT,formats.get(3).layout);
		assertEquals(SlideFormat.Layout.CENTER,formats.get(4).layout);
	}

}
