package org.geepawhill.contentment.test;

import static org.junit.Assert.assertTrue;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.Sequence;
import org.geepawhill.contentment.core.Step;
import org.geepawhill.contentment.newstep.Instant;
import org.geepawhill.contentment.newstep.InstantStep;
import org.geepawhill.contentment.outline.KvOutline;
import org.geepawhill.contentment.outline.KvVisualMatcher;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class JfxTester
{
	private Context context;
	private KvVisualMatcher matcher;
	public KvOutline beforeAll;
	private Group group;

	public JfxTester()
	{
		matcher = new KvVisualMatcher();
	}

	public void prepareWindow(Stage stage)
	{
		Pane region = new Pane();
		region.setMaxSize(1600d, 900d);
		region.setMinSize(1600d, 900d);
		group = new Group();
		region.getChildren().add(group);
		resetContext();
		stage.setScene(new Scene(region));
		stage.show();
	}

	private void resetContext()
	{
		context = new Context(group);
		beforeAll = context.outline();
	}

	public KvOutline waitForPlay(Step step)
	{
		return waitForPlay(new Sequence(step));
	}
	
	public KvOutline waitForPlay(Sequence sequence)
	{
		for(int s=0;s<sequence.size();s++)
		{
			waitForPlayNoOutline(sequence.get(s));
		}
		return context.outline();
	}
	
	public KvOutline waitForBefore(Step step)
	{
		return waitForBefore(new Sequence(step));
	}
	
	public KvOutline waitForBefore(Sequence sequence)
	{
		for(int s=sequence.size()-1;s>=0;s--)
		{
			waitForBeforeNoOutline(sequence.get(s));
		}
		return context.outline();
	}
	
	public KvOutline waitForAfter(Step step)
	{
		return waitForAfter(new Sequence(step));
	}
	
	public KvOutline waitForAfter(Sequence sequence)
	{
		for(int s=0;s<sequence.size();s++)
		{
			waitForAfterNoOutline(sequence.get(s));
		}
		return context.outline();
	}

	public void beforeSameAsPlayBefore(Step step) 
	{
		beforeSameAsPlayBefore(new Sequence(step));
	}
	
	public void beforeSameAsPlayBefore(Sequence sequence) 
	{
		resetContext();
		waitForPlay(sequence);
		KvOutline before = waitForBefore(sequence);
		matcher.assertEqual("Before Same as Play Before", beforeAll, before);
	}

	public void beforeSameAsAfterBefore(Step step)
	{
		beforeSameAsAfterBefore(new Sequence(step));
	}
	
	public void beforeSameAsAfterBefore(Sequence sequence) 
	{
		resetContext();
		waitForAfter(sequence);
		KvOutline before = waitForBefore(sequence);
		matcher.assertEqual("Change fail message!", beforeAll, before);
	}

	public void afterSameAsPlay(Step step)
	{
		afterSameAsPlay(new Sequence(step));
	}

	public void afterSameAsPlay(Sequence sequence)
	{
		resetContext();
		KvOutline after = waitForAfter(sequence);
		resetContext();
		KvOutline play = waitForPlay(sequence);
		matcher.assertEqual("Change fail message!", after, play);
	}

	public void assertEquals(KvOutline expected, KvOutline actual)
	{
		matcher.assertEqual("Change fail message!", expected, actual);
	}
	
	private void waitForPlayNoOutline(Step step)
	{
		actLater(step,(latch) -> step.play(context,()-> latch.countDown()),	"Play timed out.");
	}
	
	private void waitForAfterNoOutline(Step step)
	{
		actLater(step, (latch) -> { step.after(context); latch.countDown(); } ,"After timed out.");
	}
	
	private void waitForBeforeNoOutline(Step step)
	{
		actLater(step, (latch) -> { step.before(context); latch.countDown(); }, "Before timed out.");
	}
	
	private void actLater(Step step, Consumer<CountDownLatch> action, String message)
	{
		final CountDownLatch latch = new CountDownLatch(1);
		Platform.runLater(() -> 
		{
			action.accept(latch);
		});
		waitForlatch(latch, message);
	}
	
	private void waitForlatch(final CountDownLatch countDownLatch, String message)
	{
		try
		{
			assertTrue(message,countDownLatch.await(5000,TimeUnit.MILLISECONDS));
		}
		catch (InterruptedException e)
		{
			throw new RuntimeException(e);
		}
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
		afterSameAsPlay(sequence);
		beforeSameAsPlayBefore(sequence);
		beforeSameAsAfterBefore(sequence);
	}
}