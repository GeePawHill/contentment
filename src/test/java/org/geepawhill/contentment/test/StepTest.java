package org.geepawhill.contentment.test;

import org.geepawhill.contentment.core.Sequence;
import org.geepawhill.contentment.core.Step;
import org.geepawhill.contentment.newstep.Instant;
import org.geepawhill.contentment.newstep.InstantStep;
import org.geepawhill.contentment.outline.KeyValue;
import org.geepawhill.contentment.outline.KvVisualMatcher;
import org.testfx.framework.junit.ApplicationTest;

import javafx.stage.Stage;

public class StepTest extends ApplicationTest
{

	public StepRunner runner;
	public KvVisualMatcher matcher;

	@Override
	public void start(Stage stage) throws Exception
	{
		runner = new StepRunner();
		matcher = new KvVisualMatcher();
		runner.prepareWindow(stage);
	}
	
	public ContextOutline play(Instant instant)
	{
		return play(new InstantStep(instant));
	}

	public ContextOutline play(Step step)
	{
		return play(new Sequence(step));
	}

	public ContextOutline play(Sequence sequence)
	{
		return runner.waitForPlay(sequence);
	}

	public ContextOutline before(Step step)
	{
		return before(new Sequence(step));
	}

	public ContextOutline before(Sequence sequence)
	{
		return runner.waitForBefore(sequence);
	}

	public ContextOutline after(Step step)
	{
		return after(new Sequence(step));
	}

	public ContextOutline after(Sequence sequence)
	{
		return runner.waitForAfter(sequence);
	}

	public void assertOutlines(String message, ContextOutline expected, ContextOutline actual)
	{
		matcher.assertEqual(message, expected, actual);
	}

	public void assertBeforeEqualsPlayBefore(Instant instant)
	{
		assertBeforeEqualsPlayBefore(new InstantStep(instant));
	}

	public void assertBeforeEqualsPlayBefore(Step step)
	{
		assertBeforeEqualsPlayBefore(new Sequence(step));
	}

	public void assertBeforeEqualsPlayBefore(Sequence sequence)
	{
		runner.resetContext();
		play(sequence);
		ContextOutline before = before(sequence);
		assertOutlines("Before not equal to Play Before.", runner.beforeAll, before);
	}
	
	public void assertBeforeEqualsAfterBefore(Instant instant)
	{
		assertBeforeEqualsAfterBefore(new InstantStep(instant));
	}

	public void assertBeforeEqualsAfterBefore(Step step)
	{
		assertBeforeEqualsAfterBefore(new Sequence(step));
	}

	public void assertBeforeEqualsAfterBefore(Sequence sequence)
	{
		runner.resetContext();
		after(sequence);
		ContextOutline before = before(sequence);
		assertOutlines("Before not equal to After Before.", runner.beforeAll, before);
	}
	
	public void assertAfterEqualsPlay(Instant instant)
	{
		assertAfterEqualsPlay(new InstantStep(instant));
	}

	public void assertAfterEqualsPlay(Step step)
	{
		assertAfterEqualsPlay(new Sequence(step));
	}

	public void assertAfterEqualsPlay(Sequence sequence)
	{
		runner.resetContext();
		ContextOutline after = after(sequence);
		runner.resetContext();
		ContextOutline play = play(sequence);
		assertOutlines("After not equal to play.", after, play);
	}

	public void assertContractValid(Instant step)
	{
		assertContractValid(new Sequence(new InstantStep(step)));
	}

	public void assertContractValid(Step step)
	{
		assertContractValid(new Sequence(step));
	}

	public void assertContractValid(Sequence sequence)
	{
		assertAfterEqualsPlay(sequence);
		assertBeforeEqualsPlayBefore(sequence);
		assertBeforeEqualsAfterBefore(sequence);
	}
	
	public KeyValue assertKey(ContextOutline outline,String key)
	{
		return outline.assertKey(key);
	}

	public KeyValue assertKey(ContextOutline outline,String key,String value)
	{
		return outline.assertKey(key,value);
	}
}
