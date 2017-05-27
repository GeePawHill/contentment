package org.geepawhill.contentment.test;

import org.geepawhill.contentment.core.Sequence;
import org.geepawhill.contentment.outline.KvVisualMatcher;
import org.geepawhill.contentment.step.Step;
import org.testfx.framework.junit.ApplicationTest;

import javafx.stage.Stage;

public class SequenceTester extends ApplicationTest
{
	public SequenceRunner runner;
	public KvVisualMatcher matcher;

	@Override
	public void start(Stage stage) throws Exception
	{
		runner = new SequenceRunner();
		matcher = new KvVisualMatcher();
		runner.prepareWindow(stage);
	}
	
	public void assertContractValid(Step step)
	{
		assertContractValid(new Sequence(step));
	}

	public void assertContractValid(Sequence sequence)
	{
		assertBeforeEqualsPlayBefore(sequence);
		assertBeforeEqualsAfterBefore(sequence);
	}
	
	public ContextOutline play(Sequence sequence)
	{
		return runner.waitForPlay(sequence);
	}

	private ContextOutline before(Sequence sequence)
	{
		return runner.waitForBefore(sequence);
	}

	private ContextOutline after(Sequence sequence)
	{
		return runner.waitForAfter(sequence);
	}

	private void assertOutlines(String message, ContextOutline expected, ContextOutline actual)
	{
		matcher.assertEqual(message, expected, actual);
	}

	private void assertBeforeEqualsPlayBefore(Sequence sequence)
	{
		runner.resetContext();
		play(sequence);
		ContextOutline before = before(sequence);
		assertOutlines("Before not equal to Play Before.", runner.beforeAll, before);
	}
	
	private void assertBeforeEqualsAfterBefore(Sequence sequence)
	{
		runner.resetContext();
		after(sequence);
		ContextOutline before = before(sequence);
		assertOutlines("Before not equal to After Before.", runner.beforeAll, before);
	}
}
