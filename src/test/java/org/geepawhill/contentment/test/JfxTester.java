package org.geepawhill.contentment.test;

import static org.junit.Assert.assertTrue;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.Sequence;
import org.geepawhill.contentment.core.Step;
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

	public JfxTester()
	{
		matcher = new KvVisualMatcher();
	}

	public void prepareWindow(Stage stage)
	{
		Pane region = new Pane();
		region.setMaxSize(1600d, 900d);
		region.setMinSize(1600d, 900d);
		Group group = new Group();
		region.getChildren().add(group);
		context = new Context(group);
		beforeAll = context.outline();
		stage.setScene(new Scene(region));
		stage.show();
	}

	public KvOutline waitForPlay(Sequence sequence)
	{
		for(int s=0;s<sequence.size();s++)
		{
			waitForPlayNoOutline(sequence.get(s));
		}
		return context.outline();
	}

	public KvOutline waitForPlay(Step step)
	{
		waitForPlayNoOutline(step);
		return context.outline();
	}
	
	public KvOutline waitForBefore(Step step)
	{
		waitForBeforeNoOutline(step);
		return context.outline();
	}
	
	public KvOutline waitForBefore(Sequence sequence)
	{
		for(int s=sequence.size()-1;s>=0;s--)
		{
			waitForBeforeNoOutline(sequence.get(s));
		}
		return context.outline();
	}

	public KvOutline waitForAfter(Sequence sequence)
	{
		for(int s=0;s<sequence.size();s++)
		{
			waitForAfterNoOutline(sequence.get(s));
		}
		return context.outline();
	}

	
	public KvOutline waitForAfter(Step step)
	{
		waitForAfterNoOutline(step);
		return context.outline();
	}
	
	public void beforeSameAsPlayBefore(Step step) 
	{
		waitForPlay(step);
		KvOutline before = waitForBefore(step);
		matcher.assertEqual(beforeAll, before);
	}

	public void beforeSameAsAfterBefore(Step step)
	{
		waitForAfter(step);
		KvOutline before = waitForBefore(step);
		matcher.assertEqual(beforeAll, before);
	}

	public void afterSameAsPlay(Step step)
	{
		KvOutline after = waitForAfter(step);
		waitForBefore(step);
		KvOutline play = waitForPlay(step);
		matcher.assertEqual(after, play);
	}

	public void assertEquals(KvOutline expected, KvOutline actual)
	{
		matcher.assertEqual(expected, actual);
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
}