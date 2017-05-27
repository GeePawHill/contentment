package org.geepawhill.contentment.actor;

import org.geepawhill.contentment.actors.Stroke;
import org.geepawhill.contentment.core.Sequence;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.style.Dash;
import org.geepawhill.contentment.style.Frames;
import org.geepawhill.contentment.timing.FixedTiming;
import org.junit.Before;

public class StrokeTest
{
	private Stroke stroke;
	
	@Before
	public void before()
	{
		Format format = new Format(Frames.unspecified(),Dash.solid());
		stroke = new Stroke(new PointPair(100d,200d,300d,400d),format);
	}

	private Sequence sketch()
	{
		Sequence sequence = new Sequence();
		return stroke.sketch(sequence,new FixedTiming(1d));
	}
}
