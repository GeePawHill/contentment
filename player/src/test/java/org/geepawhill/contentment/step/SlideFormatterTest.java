package org.geepawhill.contentment.step;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

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
		for(Text text : formatter.layout(samples))
		{
			assertEquals("Line",text.getText());
		}
	}
	
	@Test
	public void titleAttributes()
	{
		Text text = formatter.layout("Line").get(0);
		assertEquals(new Font("Buxton Sketch",100d),text.getFont());
		assertEquals(Color.YELLOW,text.getStroke());
		assertEquals(Color.YELLOW,text.getFill());
	}
	
	@Test
	public void oneAttributes()
	{
		Text text = formatter.layout("+Line").get(0);
		assertEquals(new Font("Buxton Sketch",80d),text.getFont());
		assertEquals(Color.YELLOWGREEN,text.getStroke());
		assertEquals(Color.YELLOWGREEN,text.getFill());
	}
	
	@Test
	public void twoAttributes()
	{
		Text text = formatter.layout("++Line").get(0);
		assertEquals(new Font("Buxton Sketch",60d),text.getFont());
		assertEquals(Color.BLUE,text.getStroke());
		assertEquals(Color.BLUE,text.getFill());
	}
	
	@Test
	public void threeAttributes()
	{
		Text text = formatter.layout("+++Line").get(0);
		assertEquals(new Font("Buxton Sketch",40d),text.getFont());
		assertEquals(Color.RED,text.getStroke());
		assertEquals(Color.RED,text.getFill());
	}
	
	@Test
	public void centerAttributes()
	{
		Text text = formatter.layout("=Line").get(0);
		assertEquals(new Font("Buxton Sketch",100d),text.getFont());
		assertEquals(Color.YELLOW,text.getStroke());
		assertEquals(Color.YELLOW,text.getFill());
	}
	
	@Test
	public void verticalStack()
	{
		List<Text> texts = formatter.layout(samples);
		double lastY=SlideFormatter.VMARGIN;
		for(Text text : texts)
		{
			assertEquals(lastY,text.getY(),0.5d);
			lastY = text.getBoundsInParent().getMaxY();
		}
	}
	
	@Test
	public void alignments()
	{
		List<Text> texts = formatter.layout(samples);
		assertLeft(texts.get(0));
		assertRight(texts.get(1));
		assertLeft(texts.get(2));
		assertIndent(texts.get(3));
		assertCentered(texts.get(4));
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

		List<Text> formats = formatter.layout(longSlide);
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

	private void assertLeft(Text text)
	{
		assertEquals(SlideFormatter.HMARGIN, text.getX(),.5d);
	}

	private void assertIndent(Text text)
	{
		assertEquals(SlideFormatter.HMARGIN*2, text.getX(),.5d);
	}

	private void assertCentered(Text text)
	{
		assertEquals(800d, (text.getBoundsInParent().getMinX()+text.getBoundsInParent().getMaxX())/2d,.5d);
	}

	private void assertRight(Text text)
	{
		assertEquals(1600d-SlideFormatter.HMARGIN, text.getBoundsInParent().getMaxX(),1d);
	}

}
