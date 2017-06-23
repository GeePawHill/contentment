package org.geepawhill.contentment.test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import org.geepawhill.contentment.core.Animator;
import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.ContextInterpolator;
import org.geepawhill.contentment.rhythm.SimpleRhythm;
import org.geepawhill.contentment.step.Step;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class JavaFxRunner
{
	public Context context;
	public Group group;

	public void prepareWindow(Stage stage)
	{
		Pane region = new Pane();
		region.setMaxSize(1600d, 900d);
		region.setMinSize(1600d, 900d);
		group = new Group();
		context = new Context(group, new SimpleRhythm());
		region.getChildren().add(group);
		stage.setScene(new Scene(region));
		stage.show();
	}

	public void play(Animator animator, double ms, ContextInterpolator interpolator)
	{
		Consumer<CountDownLatch> action = new Consumer<CountDownLatch>()
		{
			@Override
			public void accept(CountDownLatch latch)
			{
				animator.play(context, () -> latch.countDown(), ms, interpolator);
			}
		};
		actLater(action);
	}

	public void slow(Step step)
	{
		Consumer<CountDownLatch> action = new Consumer<CountDownLatch>()
		{
			@Override
			public void accept(CountDownLatch latch)
			{
				step.slow(context, () -> latch.countDown());
			}
		};
		actLater(action);
	}

	public void fast(Step step)
	{
		Consumer<CountDownLatch> action = new Consumer<CountDownLatch>()
		{
			@Override
			public void accept(CountDownLatch latch)
			{
				step.fast(context);
				latch.countDown();
			}
		};
		actLater(action);
	}

	public void undo(Step step)
	{
		Consumer<CountDownLatch> action = new Consumer<CountDownLatch>()
		{
			@Override
			public void accept(CountDownLatch latch)
			{
				step.undo(context);
				latch.countDown();
			}
		};
		actLater(action);
	}

	public void actLater(Consumer<CountDownLatch> action)
	{
		final CountDownLatch latch = new CountDownLatch(1);
		Platform.runLater(() -> action.accept(latch));
		waitForlatch(latch);
	}

	private void waitForlatch(final CountDownLatch latch)
	{
		try
		{
			boolean finished = latch.await(5000, TimeUnit.MILLISECONDS);
			if (!finished) throw new RuntimeException("Wait timed out.");
		}
		catch (InterruptedException e)
		{
			throw new RuntimeException(e);
		}
	}
}