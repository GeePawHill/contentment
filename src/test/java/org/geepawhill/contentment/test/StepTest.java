package org.geepawhill.contentment.test;

import org.geepawhill.contentment.core.Sequence;
import org.geepawhill.contentment.core.Step;
import org.geepawhill.contentment.newstep.Instant;
import org.geepawhill.contentment.newstep.InstantStep;
import org.geepawhill.contentment.outline.KeyValue;
import org.geepawhill.contentment.outline.KvOutline;
import org.geepawhill.contentment.outline.KvVisualMatcher;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.*;

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
	
	public KvOutline play(Instant instant)
	{
		return play(new InstantStep(instant));
	}

	public KvOutline play(Step step)
	{
		return play(new Sequence(step));
	}

	public KvOutline play(Sequence sequence)
	{
		return runner.waitForPlay(sequence);
	}

	public KvOutline before(Step step)
	{
		return before(new Sequence(step));
	}

	public KvOutline before(Sequence sequence)
	{
		return runner.waitForBefore(sequence);
	}

	public KvOutline after(Step step)
	{
		return after(new Sequence(step));
	}

	public KvOutline after(Sequence sequence)
	{
		return runner.waitForAfter(sequence);
	}

	public void assertOutlines(String message, KvOutline expected, KvOutline actual)
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
		KvOutline before = before(sequence);
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
		KvOutline before = before(sequence);
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
		KvOutline after = after(sequence);
		runner.resetContext();
		KvOutline play = play(sequence);
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
	
	public KeyValue assertKey(KvOutline outline,String key)
	{
		KeyValue found = outline.find(key);
		if(found==null)
		{
			outline.dump("Outline");
			System.out.println("Missing key: "+key);
			fail("Key not found: ["+key+"]");
		}
		return found;
	}

	public void assertKey(KvOutline outline,String key,String value)
	{
		KeyValue found = assertKey(outline,key);
		assertEquals(value,found.getValue());
	}

	
}
