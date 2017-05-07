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
		assertEquals(new Font("Buxton Sketch",40d),format.text.getFont());
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
		assertLeft(formats.get(0));
		assertRight(formats.get(1));
		assertLeft(formats.get(2));
		assertIndent(formats.get(3));
		assertCentered(formats.get(4));
	}
	
	@Test
	public void longSlideAlignments()
	{
		String[] longSlide = {
				"0Complexity Blah-Blah-Blah",
				"+1There’s Tons Of Theory",
				"++2non-linear dynamics, chaos theory (math)",
				"++3complex adaptive systems (systems theory)",
				"++4ecology, organism (biology)",
				"++5the mangle (science studies)",
				"++6anything & everything (history)",
				"+7We Need *Practice*",
				"++8actual direct advice",
				"++9a pause in the flavor wars",
				"++10no rulesets or drop-in systems",
				"++11forces & relationships",
				"+12And We Already Have Some",
				"=13we can see the entire agile movement as a response",
				"=14find the parts of agility that work",
				};

		List<SlideFormat> formats = formatter.layout(longSlide);
		assertLeft(formats.get(0));
		assertRight(formats.get(1));
		assertLeft(formats.get(2));
		assertLeft(formats.get(3));
		assertLeft(formats.get(4));
		assertLeft(formats.get(5));
		assertLeft(formats.get(6));
		assertRight(formats.get(7));
		assertLeft(formats.get(8));
		assertLeft(formats.get(9));
		assertLeft(formats.get(10));
		assertLeft(formats.get(11));
		assertRight(formats.get(12));
		assertCentered(formats.get(13));
		assertCentered(formats.get(14));

	}


	private void assertLeft(SlideFormat format)
	{
		assertEquals(SlideFormat.Layout.LEFT,format.layout);
		assertEquals(SlideFormatter.HMARGIN, format.text.getX(),.5d);
	}

	private void assertIndent(SlideFormat format)
	{
		assertEquals(SlideFormat.Layout.INDENT,format.layout);
		assertEquals(SlideFormatter.HMARGIN*2, format.text.getX(),.5d);
	}

	private void assertCentered(SlideFormat format)
	{
		assertEquals(SlideFormat.Layout.CENTER,format.layout);
		assertEquals(800d, (format.text.getBoundsInParent().getMinX()+format.text.getBoundsInParent().getMaxX())/2d,.5d);
	}

	private void assertRight(SlideFormat format)
	{
		assertEquals(SlideFormat.Layout.RIGHT,format.layout);
		assertEquals(1600d-SlideFormatter.HMARGIN, format.text.getBoundsInParent().getMaxX(),1d);
	}

}
