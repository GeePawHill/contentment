package org.geepawhill.contentment.step;

import org.geepawhill.contentment.actor.LabelBox;
import org.geepawhill.contentment.core.Sequence;
import org.geepawhill.contentment.model.Step;
import org.geepawhill.contentment.test.SequenceTester;
import org.junit.Before;
import org.junit.Test;

public class LabelBoxSketchTest extends SequenceTester
{
	private LabelBox label;

	@Before
	public void before()
	{
		label = new LabelBox("Hi Mom!",800d,450d);
	}

	@Test
	public void playChangesText() throws Exception
	{
		Step step = label.sketch(1d);
		play(new Sequence(step));
	}
	
	@Test
	public void contract() throws Exception
	{
		assertContractValid(label.sketch(1d));
	}
}
