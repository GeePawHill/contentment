package org.geepawhill.contentment.actor;

import org.geepawhill.contentment.core.Sequence;
import org.geepawhill.contentment.geometry.Point;
import org.geepawhill.contentment.newstep.SetStyleStep;
import org.geepawhill.contentment.style.ShapePen;
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
		stroke = new Letters("Some text.",new Point(300d,200d));
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
		outline.assertBase("Current","Some text.");
		outline.assertBase("Visible","true");
		outline.assertBase("Opacity","0.5");
		outline.assertBase("LineColor","0xff0000ff");
		outline.assertBase("Bounds","(180,149) - (415,223)");
	}

	private Sequence sketch()
	{
		Sequence sequence = new Sequence();
		sequence.add(new SetStyleStep(ShapePen.first()));
		return stroke.sketch(sequence,new FixedTiming(1d));
	}
}
