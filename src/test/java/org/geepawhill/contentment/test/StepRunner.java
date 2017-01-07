package org.geepawhill.contentment.test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import org.geepawhill.contentment.actor.Names;
import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.Sequence;
import org.geepawhill.contentment.core.Step;
import org.geepawhill.contentment.outline.KvOutline;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class StepRunner
{
	private Context context;
	public KvOutline beforeAll;
	private Group group;

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

	public void resetContext()
	{
		Names.reset();
		context = new Context(group);
		beforeAll = context.outline();
	}

	public KvOutline waitForPlay(Sequence sequence)
	{
		for (int s = 0; s < sequence.size(); s++)
		{
			playWithLatch(sequence.get(s));
		}
		return context.outline();
	}

	public KvOutline waitForBefore(Sequence sequence)
	{
		for (int s = sequence.size() - 1; s >= 0; s--)
		{
			beforeWithLatch(sequence.get(s));
		}
		return context.outline();
	}

	public KvOutline waitForAfter(Sequence sequence)
	{
		for (int s = 0; s < sequence.size(); s++)
		{
			afterWithLatch(sequence.get(s));
		}
		return context.outline();
	}

	private void playWithLatch(Step step)
	{
		Consumer<CountDownLatch> action = new Consumer<CountDownLatch>()
		{
			@Override
			public void accept(CountDownLatch latch)
			{
				step.play(context, () -> latch.countDown() );
			}
		};
		actLater(step, action);
	}

	private void afterWithLatch(Step step)
	{
		Consumer<CountDownLatch> action = new Consumer<CountDownLatch>()
		{
			@Override
			public void accept(CountDownLatch latch)
			{
				step.after(context);
				latch.countDown();
			}
		};
		actLater(step, action);
	}

	private void beforeWithLatch(Step step)
	{
		Consumer<CountDownLatch> action = new Consumer<CountDownLatch>()
		{
			@Override
			public void accept(CountDownLatch latch)
			{
				step.before(context);
				latch.countDown();
			}
		};
		actLater(step, action);
	}

	private void actLater(Step step, Consumer<CountDownLatch> action)
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