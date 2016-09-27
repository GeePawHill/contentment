package org.geepawhill.contentment;

import static org.junit.Assert.fail;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

@RunWith(JfxTestRunner.class)
public class StepBaseTest
{

	protected EventHandler<ActionEvent> onFinished;

	@Ignore
	@Test
	public void failsOkay()
	{
		System.out.println("Test2 running.");
		fail("Let's see a test fail.");
	}

	@Test
	public void animationTest()
	{
		System.out.println("About to animate.");
		Transition animation = new Transition()
		{

			{
				setCycleDuration(Duration.millis(3000d));
			}

			@Override
			protected void interpolate(double frac)
			{
				System.out.println("Interpolate: " + frac);
			}
		};

		playAnimationAndWaitForFinish(animation);
		System.out.println("Animation played.");
	}

	private synchronized void playAnimationAndWaitForFinish(final Animation animation)
	{
		if (Platform.isFxApplicationThread()) throw new IllegalThreadStateException("Cannot be executed on main JavaFX thread");
		final Thread currentThread = Thread.currentThread();
		final EventHandler<ActionEvent> originalOnFinished = animation.getOnFinished();
		animation.setOnFinished(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent event)
			{
				if (originalOnFinished != null)
				{
					originalOnFinished.handle(event);
				}
				synchronized (currentThread)
				{
					currentThread.notify();
				}
			}
		});
		Platform.runLater(new Runnable()
		{

			@Override
			public void run()
			{
				animation.play();
			}
		});
		synchronized (currentThread)
		{
			try
			{
				currentThread.wait();
			}
			catch (InterruptedException ex)
			{
				// somebody interrupted me, OK
			}
		}
	}

}
