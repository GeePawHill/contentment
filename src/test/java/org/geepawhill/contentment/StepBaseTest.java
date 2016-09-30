package org.geepawhill.contentment;

import static org.junit.Assert.*;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.Step;
import org.geepawhill.contentment.step.TransitionStep;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
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
		Pane region = new Pane();
		region.setMaxSize(1600d, 900d);
		region.setMinSize(1600d, 900d);
		Group group = new Group();
		region.getChildren().add(group);
		Context context = new Context(group);
		Text label = new Text(800d,450d,"");
		
		Transition animation = new Transition()
		{
			{
				setCycleDuration(Duration.millis(3000d));
			}

			@Override
			protected void interpolate(double frac)
			{
				if(frac==0d)
				{
					System.out.println("0");
				}
				String text = "Hi Mom!";
				String newText = text.substring(0, (int) (frac * text.length()));
				label.setText(newText);
				label.setX(800d-label.getBoundsInParent().getWidth()/2d);
				label.setY(450d);

				label.setText(newText);
				System.out.println("Interpolate: " + frac+" "+newText);
			}
		};
		TransitionStep step = new TransitionStep(animation);
		waitForFinish(context,step);
		assertEquals("Hi Mom!",label.getText());
		System.out.println("Animation played.");
	}
	

	private void waitForFinish(Context context,Step step)
	{
		if (Platform.isFxApplicationThread()) throw new IllegalThreadStateException("Cannot be executed on main JavaFX thread");
		final Thread currentThread = Thread.currentThread();
		context.onFinished=(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent event)
			{
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
				step.play(context);
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
