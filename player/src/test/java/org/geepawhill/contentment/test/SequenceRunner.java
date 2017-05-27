package org.geepawhill.contentment.test;

import org.geepawhill.contentment.core.Context;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SequenceRunner
{
	public Context context;
	public ContextOutline beforeAll;
	public Group group;

	public void prepareWindow(Stage stage)
	{
		Pane region = new Pane();
		region.setMaxSize(1600d, 900d);
		region.setMinSize(1600d, 900d);
		group = new Group();
		region.getChildren().add(group);
//		resetContext();
		stage.setScene(new Scene(region));
		stage.show();
	}

//	public void resetContext()
//	{
//		Names.reset();
//		context = new Context(group);
////		beforeAll = new ContextOutline(context.outline());
//	}
//
//	public ContextOutline waitForPlay(Sequence sequence)
//	{
//		for (int s = 0; s < sequence.size(); s++)
//		{
//			playWithLatch(sequence.get(s));
//		}
//		return new ContextOutline(context.outline());
//	}
//
//	public ContextOutline waitForBefore(Sequence sequence)
//	{
//		for (int s = sequence.size() - 1; s >= 0; s--)
//		{
//			beforeWithLatch(sequence.get(s));
//		}
//		return new ContextOutline(context.outline());
//	}
//
//	public ContextOutline waitForAfter(Sequence sequence)
//	{
//		for (int s = 0; s < sequence.size(); s++)
//		{
//			afterWithLatch(sequence.get(s));
//		}
//		return new ContextOutline(context.outline());
//	}
//
//	private void playWithLatch(Step step)
//	{
//		Consumer<CountDownLatch> action = new Consumer<CountDownLatch>()
//		{
//			@Override
//			public void accept(CountDownLatch latch)
//			{
//				step.play(context, () -> latch.countDown() );
//			}
//		};
//		actLater(action);
//	}
//
//	private void afterWithLatch(Step step)
//	{
//		Consumer<CountDownLatch> action = new Consumer<CountDownLatch>()
//		{
//			@Override
//			public void accept(CountDownLatch latch)
//			{
//				step.after(context);
//				latch.countDown();
//			}
//		};
//		actLater(action);
//	}
//
//	private void beforeWithLatch(Step step)
//	{
//		Consumer<CountDownLatch> action = new Consumer<CountDownLatch>()
//		{
//			@Override
//			public void accept(CountDownLatch latch)
//			{
//				step.unplay(context);
//				latch.countDown();
//			}
//		};
//		actLater(action);
//	}
//
//	public void actLater(Consumer<CountDownLatch> action)
//	{
//		final CountDownLatch latch = new CountDownLatch(1);
//		Platform.runLater(() -> action.accept(latch));
//		waitForlatch(latch);
//	}
//
//	private void waitForlatch(final CountDownLatch latch)
//	{
//		try
//		{
//			boolean finished = latch.await(5000, TimeUnit.MILLISECONDS);
//			if (!finished) throw new RuntimeException("Wait timed out.");
//		}
//		catch (InterruptedException e)
//		{
//			throw new RuntimeException(e);
//		}
//	}
}