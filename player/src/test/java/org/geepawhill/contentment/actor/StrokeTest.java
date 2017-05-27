package org.geepawhill.contentment.actor;

import org.geepawhill.contentment.actors.Stroke;
import org.geepawhill.contentment.core.Sequence;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.style.Dash;
import org.geepawhill.contentment.style.Frames;
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
		Format format = new Format(Frames.unspecified(),Dash.solid());
		stroke = new Stroke(new PointPair(100d,200d,300d,400d),format);
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
