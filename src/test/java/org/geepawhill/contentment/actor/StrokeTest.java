package org.geepawhill.contentment.actor;

import org.geepawhill.contentment.core.Sequence;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.step.styles.SetStyle;
import org.geepawhill.contentment.style.ShapePen;
import org.geepawhill.contentment.test.ContextOutline;
import org.geepawhill.contentment.test.SequenceTester;
import org.geepawhill.contentment.timing.FixedTiming;
import org.junit.Before;
import org.junit.Test;

public class StrokeTest extends SequenceTester
{
	private Stroke stroke;
	
	@Before
	public void before()
	{
		stroke = new Stroke(new PointPair(100d,200d,300d,400d));
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
		outline.baseKey("Actors.Stroke_1.Line");
		outline.assertBase("Visible","true");
		outline.assertBase("Opacity","0.5");
		outline.assertBase("LineColor","0xff0000ff");
		outline.assertBase("Bounds","(96,196) - (304,404)");
	}

	private Sequence sketch()
	{
		Sequence sequence = new Sequence();
		sequence.add(new SetStyle(ShapePen.first()));
		return stroke.sketch(sequence,new FixedTiming(1d));
	}
}
