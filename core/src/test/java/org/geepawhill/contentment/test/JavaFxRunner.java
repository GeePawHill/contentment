package org.geepawhill.contentment.test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import org.geepawhill.contentment.core.Fragment;
import org.geepawhill.contentment.core.FragmentTransition;
import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.step.Step;

import javafx.animation.Transition;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class JavaFxRunner
{
	public Context context;

	public void prepareWindow(Stage stage)
	{
		Pane region = new Pane();
		region.setMaxSize(1600d, 900d);
		region.setMinSize(1600d, 900d);
		context = new Context();
		region.getChildren().add(context.canvas);
		stage.setScene(new Scene(region));
		stage.show();
	}
	
	public void play(Transition transition)
	{
		Consumer<CountDownLatch> action = new Consumer<CountDownLatch>()
		{
			@Override
			public void accept(CountDownLatch latch)
			{
				transition.setOnFinished((event) -> latch.countDown());
				transition.play();
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

	public void play(long ms, Fragment atom)
	{
		Consumer<CountDownLatch> action = new Consumer<CountDownLatch>()
		{
			@Override
			public void accept(CountDownLatch latch)
			{
				new FragmentTransition(ms,atom,context,()->latch.countDown()).play();
			}
		};
		actLater(action);
	}
}