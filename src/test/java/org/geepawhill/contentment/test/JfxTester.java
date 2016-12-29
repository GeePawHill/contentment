package org.geepawhill.contentment.test;

import static org.junit.Assert.assertTrue;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import org.geepawhill.contentment.core.Context;
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
	private KvOutline beforeAll;

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

	public KvOutline waitForPlay(Step step)
	{
		playLater(step);
		return context.outline();
	}
	
	public KvOutline waitForBefore(Step step)
	{
		beforeLater(step);
		return context.outline();
	}

	public KvOutline waitForAfter(Step step)
	{
		afterLater(step);
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
	
	private void playLater(Step step)
	{
		actLater(step,(latch) -> step.play(context,()-> latch.countDown()),	"Play timed out.");
	}
	
	private void afterLater(Step step)
	{
		actLater(step, (latch) -> { step.after(context); latch.countDown(); } ,"After timed out.");
	}
	
	private void beforeLater(Step step)
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