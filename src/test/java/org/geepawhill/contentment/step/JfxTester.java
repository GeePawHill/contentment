package org.geepawhill.contentment.step;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.CountDownLatch;

import org.geepawhill.contentment.core.Actor;
import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.Snap;
import org.geepawhill.contentment.core.Step;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class JfxTester
{
	private Context context;

	public void waitForPlay(Step step) throws Exception
	{
		final CountDownLatch countDownLatch = new CountDownLatch(1);
		context.onFinished = (event) -> {
	        countDownLatch.countDown();
		};
		Platform.runLater(() -> {
			step.play(context);
		});
		countDownLatch.await();
	}

	public void prepareWindow(Stage stage)
	{
		Pane region = new Pane();
		region.setMaxSize(1600d, 900d);
		region.setMinSize(1600d, 900d);
		Group group = new Group();
		region.getChildren().add(group);
		context = new Context(group);
		stage.setScene(new Scene(region));
		stage.show();
	}
	
	public void properties(Actor actor,String... properties)
	{
		Snap snap = actor.snap();
		for(String property : properties)
		{
			assertNotNull(snap.get(property));
		}
	}
	
	public void assertProperty(Actor actor,String property, String expected)
	{
		String result = actor.snap().asString(property);
		assertNotNull(result);
		assertEquals(expected,result);
	}

	public void waitForBefore(Step step) throws Exception
	{
		final CountDownLatch countDownLatch = new CountDownLatch(1);
		Platform.runLater(() -> {
			step.before(context);
			countDownLatch.countDown();
		});
		countDownLatch.await();
	}
	
	public void waitForAfter(Step step) throws Exception
	{
		final CountDownLatch countDownLatch = new CountDownLatch(1);
		Platform.runLater(() -> {
			step.after(context);
			countDownLatch.countDown();
		});
		countDownLatch.await();
	}

	public void beforeBeforesAfterPlay(Actor actor, Step step) throws Exception
	{
		Snap before = actor.snap();
		waitForPlay(step);
		Snap after = actor.snap();
		assertFalse(before.isEqual(after, false));
		waitForBefore(step);
		Snap afterReset = actor.snap();
		assertTrue(before.isEqual(afterReset, false));
	}

	public void beforeBeforesAfterAfter(Actor actor, Step step) throws Exception
	{
		Snap before = actor.snap();
		waitForAfter(step);
		Snap after = actor.snap();
		assertFalse(before.isEqual(after, false));
		waitForBefore(step);
		Snap afterReset = actor.snap();
		assertTrue(before.isEqual(afterReset, true));
	}

	public void afterEqualsPlay(Actor actor, Step step) throws Exception
	{
		Snap before = actor.snap();
		waitForAfter(step);
		Snap after = actor.snap();
		assertFalse(before.isEqual(after, false));
		waitForBefore(step);
		waitForPlay(step);
		Snap afterReset = actor.snap();
		assertTrue(after.isEqual(afterReset, false));
	}
}
