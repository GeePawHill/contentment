package org.geepawhill.contentment.step;

import org.geepawhill.contentment.actor.Label;
import org.geepawhill.contentment.core.Sequence;
import org.geepawhill.contentment.core.Step;
import org.geepawhill.contentment.test.StepRunner;
import org.geepawhill.contentment.test.StepTest;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import javafx.stage.Stage;

public class LabelSketchTest extends StepTest
{
	private Label label;

	@Before
	public void before()
	{
		label = new Label("Hi Mom!",800d,450d);
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

	@Test
	public void beforeResetsText() throws Exception
	{
		Step step = label.sketch(1d);
		after(new Sequence(step));
		before(new Sequence(step));
	}
}
