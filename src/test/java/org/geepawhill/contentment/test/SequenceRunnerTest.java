package org.geepawhill.contentment.test;

import org.geepawhill.contentment.core.Sequence;
import org.geepawhill.contentment.core.StyleId;
import org.geepawhill.contentment.newstep.RestoreStylesStep;
import org.geepawhill.contentment.newstep.SaveStylesStep;
import org.geepawhill.contentment.newstep.SetStyleStep;
import org.geepawhill.contentment.style.ShapePen;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import javafx.stage.Stage;


public class SequenceRunnerTest extends ApplicationTest
{

	private SequenceRunner runner;
	private Sequence sequence;
	
	@Override
	public void start(Stage stage) throws Exception
	{
		runner = new SequenceRunner();
		runner.prepareWindow(stage);
		
		sequence = new Sequence();
		sequence.add(new SaveStylesStep());
		sequence.add(new SetStyleStep(ShapePen.second()));
		sequence.add(new RestoreStylesStep());
	}
	
	@Test
	public void playSequence()
	{
		ContextOutline play = runner.waitForPlay(sequence);
		play.assertKey("Styles."+StyleId.ShapePen.name(),ShapePen.second().toString());
	}
	
	@Test
	public void afterSequence()
	{
		ContextOutline after = runner.waitForAfter(sequence);
		after.assertKey("Styles."+StyleId.ShapePen.name(),ShapePen.second().toString());
	}
	
	@Test
	public void beforeSequence()
	{
		runner.waitForPlay(sequence);
		ContextOutline before = runner.waitForBefore(sequence);
		before.assertKey("Styles."+StyleId.ShapePen.name(),ShapePen.first().toString());
	}
}
