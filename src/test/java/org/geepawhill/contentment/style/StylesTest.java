package org.geepawhill.contentment.style;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.geepawhill.contentment.outline.KeyValue;
import org.geepawhill.contentment.outline.KvOutline;
import org.geepawhill.contentment.outline.Line;
import org.junit.Before;
import org.junit.Test;

import javafx.scene.paint.Color;

public class StylesTest
{

	private Styles styles;
	private Style redLine;
	private Style blackLine;
	
	@Before
	public void before()
	{
		styles = new Styles();
		redLine = LineColor.lineColor("RED", Color.RED);
		blackLine = LineColor.lineColor("BLACK", Color.BLACK);
	}

	@Test(expected=RuntimeException.class)
	public void throwsOnMissing()
	{
		styles.get(StyleId.LineColor);
	}
	
	@Test
	public void setAndGet()
	{
		styles.set(redLine);
		assertEquals(redLine,styles.get(StyleId.LineColor));
	}

	@Test
	public void getAfterPush()
	{
		styles.set(redLine);
		styles.push();
		assertEquals(redLine,styles.get(StyleId.LineColor));
	}
	
	@Test
	public void getNewAfterNewSetAfterPush()
	{
		styles.set(redLine);
		styles.push();
		styles.set(blackLine);
		assertEquals(blackLine,styles.get(StyleId.LineColor));
	}
	
	@Test
	public void getOldAfterNewSetAfterPop()
	{
		styles.set(redLine);
		styles.push();
		styles.set(blackLine);
		styles.pop();
		assertEquals(redLine,styles.get(StyleId.LineColor));
	}

	@Test(expected=RuntimeException.class)
	public void tooManyPops()
	{
		styles.pop();
		styles.pop();
	}
	
	@Test
	public void dump()
	{
		KvOutline tree = new KvOutline();
		styles.set(redLine);
		styles.push();
		styles.set(blackLine);
		styles.dump(tree);
		List<Line<KeyValue>> output = tree.asList();
		assertEquals(5,output.size());
		assertEquals("Styles",output.get(0).data.getKey());
		assertEquals("Map0",output.get(1).data.getKey());
		assertEquals("LineColor = BLACK (0x000000ff)",output.get(2).data.toString());
		assertEquals("Map1",output.get(3).data.getKey());
		assertEquals("LineColor = RED (0xff0000ff)",output.get(4).data.toString());
	}
	
}
