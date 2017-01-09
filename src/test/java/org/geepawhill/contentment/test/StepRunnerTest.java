package org.geepawhill.contentment.test;

import static org.junit.Assert.assertEquals;

import org.geepawhill.contentment.core.Sequence;
import org.geepawhill.contentment.step.styles.GetStyles;
import org.geepawhill.contentment.step.styles.SetStyle;
import org.geepawhill.contentment.step.styles.SetStyles;
import org.geepawhill.contentment.style.LineColor;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import javafx.stage.Stage;


public class StepRunnerTest extends ApplicationTest
{

	private StepRunner runner;
	private Sequence sequence;
	
	@Override
	public void start(Stage stage) throws Exception
	{
		runner = new StepRunner();
		runner.prepareWindow(stage);
		
		sequence = new Sequence();
		sequence.add(new GetStyles());
		sequence.add(new SetStyle(LineColor.red()));
		sequence.add(new SetStyles());
	}
	
	@Test
	public void playSequence()
	{
		ContextOutline play = runner.waitForPlay(sequence);
		assertEquals(LineColor.red().toString(),play.find("Styles.LineColor").getValue());
	}
	
	@Test
	public void afterSequence()
	{
		ContextOutline after = runner.waitForAfter(sequence);
		assertEquals(LineColor.red().toString(),after.find("Styles.LineColor").getValue());
	}
	
	@Test
	public void beforeSequence()
	{
		runner.waitForPlay(sequence);
		ContextOutline before = runner.waitForBefore(sequence);
		assertEquals(LineColor.black().toString(),before.find("Styles.LineColor").getValue());
	}
}
