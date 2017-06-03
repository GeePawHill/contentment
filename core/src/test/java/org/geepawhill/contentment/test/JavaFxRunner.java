package org.geepawhill.contentment.test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import org.geepawhill.contentment.core.Animator;
import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.ContextInterpolator;

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
		region.getChildren().add(group);
		stage.setScene(new Scene(region));
		stage.show();
	}

	public void waitForPlay(Animator animator, double ms, ContextInterpolator interpolator)
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

	// public void waitForPlay(Sequence sequence)
	// {
	// for (int s = 0; s < sequence.size(); s++)
	// {
	// playWithLatch(sequence.get(s));
	// }
	// }
	//
	// public void waitForAfter(Sequence sequence)
	// {
	// for (int s = 0; s < sequence.size(); s++)
	// {
	// afterWithLatch(sequence.get(s));
	// }
	// }

	// private void playWithLatch(Step step)
	// {
	// Consumer<CountDownLatch> action = new Consumer<CountDownLatch>()
	// {
	// @Override
	// public void accept(CountDownLatch latch)
	// {
	// step.play(context, () -> latch.countDown() );
	// }
	// };
	// actLater(action);
	// }
	//
	// private void afterWithLatch(Step step)
	// {
	// Consumer<CountDownLatch> action = new Consumer<CountDownLatch>()
	// {
	// @Override
	// public void accept(CountDownLatch latch)
	// {
	// step.after(context);
	// latch.countDown();
	// }
	// };
	// actLater(action);
	// }
	//
	// private void beforeWithLatch(Step step)
	// {
	// Consumer<CountDownLatch> action = new Consumer<CountDownLatch>()
	// {
	// @Override
	// public void accept(CountDownLatch latch)
	// {
	// step.unplay(context);
	// latch.countDown();
	// }
	// };
	// actLater(action);
	// }

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