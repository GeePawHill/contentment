package org.geepawhill.contentment.actor;


import org.geepawhill.contentment.core.Sequence;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.geometry.Point;
import org.geepawhill.contentment.style.TextColors;
import org.geepawhill.contentment.style.TextFont;
import org.geepawhill.contentment.test.ContextOutline;
import org.geepawhill.contentment.test.SequenceTester;
import org.geepawhill.contentment.timing.FixedTiming;
import org.junit.Before;
import org.junit.Test;

public class LettersTest extends SequenceTester
{
	private Letters stroke;
	
	@Before
	public void before()
	{
		stroke = new Letters("Some text.",new Point(300d,200d), new Format(TextFont.unspecified(),TextColors.unspecified()));
	}

	@Test
	public void sketchContract()
	{
		assertContractValid(sketch());
	}
	
	@Test
	public void sketchProperties()
	{
		ContextOutline outline = play(sketch());
		outline.assertKey("Actors.Letters_1.Source","Some text.");
		outline.baseKey("Actors.Letters_1.Text");
		outline.assertBase("Current");
		outline.assertBase("Visible");
		outline.assertBase("Opacity");
		outline.assertBase("LineColor");
		outline.assertBase("Bounds");
	}

	private Sequence sketch()
	{
		Sequence sequence = new Sequence();
		return stroke.sketch(sequence,new FixedTiming(1d));
	}
}
