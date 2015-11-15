package org.geepawhill.contentment;

import javafx.animation.Animation;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import org.junit.BeforeClass;

public class JavaFxTest
{

	public static class AsNonApp extends Application
	{
		public static AsNonApp app;
		public Pane pane;

		@Override
		public void start(Stage primaryStage) throws Exception
		{
			app = this;
			pane = new Pane();
			Scene scene = new Scene(pane,400d,400d);
			primaryStage.setScene(scene);
			primaryStage.show();
		}

	}

	@BeforeClass
	public static void beforeClass() throws InterruptedException
	{
		launchJavaFxThread();
	}

	private static void launchJavaFxThread()
	{
		Thread javaFxThread = new Thread("JavaFX Init Thread")
		{
			public void run()
			{
				Application.launch(AsNonApp.class, new String[0]);
			}
		};
		javaFxThread.setDaemon(true);
		javaFxThread.start();
		System.out.printf("FX App thread started\n");
		try
		{
			Thread.sleep(500);
		}
		catch (InterruptedException e)
		{
		}
	}

	protected synchronized void waitFor(final Animation animation)
	{
		cantRunOnJavaFxThread();
		final Thread currentThread = Thread.currentThread();
		replaceOnFinishedWithThreadAwareVersion(animation, currentThread);
		queueAnimationPlay(animation);
		waitCurrentThread(currentThread);
	}
	
	protected synchronized void waitFor(final Runnable runnable)
	{
		cantRunOnJavaFxThread();
		final Thread currentThread = Thread.currentThread();
		Runnable wrapper = new Runnable() {

			@Override
			public void run()
			{
				runnable.run();
				synchronized (currentThread)
				{
					currentThread.notify();
				}
				
			}};
		Platform.runLater(wrapper);
		waitCurrentThread(currentThread);
	}
	
	private void waitCurrentThread(final Thread currentThread)
	{
		synchronized (currentThread)
		{
			try
			{
				currentThread.wait();
				System.out.println("Waited.");
			}
			catch (InterruptedException expected)
			{
				// somebody interrupted me, OK
				System.out.println("Waited. Interrupted.");
			}
		}
	}

	private void queueAnimationPlay(final Animation animation)
	{
		Platform.runLater(new Runnable()
		{

			@Override
			public void run()
			{
				animation.play();
			}
		});
	}

	private void replaceOnFinishedWithThreadAwareVersion(final Animation animation, final Thread currentThread)
	{
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
	}

	private void cantRunOnJavaFxThread()
	{
		if (Platform.isFxApplicationThread())
		{
			// this comment forces formatting i like. :)
			throw new IllegalThreadStateException("Cannot be executed on main JavaFX thread");
		}
	}
	
	protected AsNonApp app()
	{
		return AsNonApp.app;
	}

}