package org.geepawhill.contentment.step;

import static org.junit.Assert.*;

import org.geepawhill.contentment.core.Actor;
import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.Snap;
import org.geepawhill.contentment.core.Step;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.layout.Pane;

public class JfxTester
{
	public void finish(Context context, Step step)
	{
		if (Platform.isFxApplicationThread()) throw new IllegalThreadStateException("Cannot be executed on main JavaFX thread");
		final Thread thread = Thread.currentThread();
		context.onFinished=(new EventHandler<ActionEvent>()
		{
	
			@Override
			public void handle(ActionEvent event)
			{
				synchronized (thread)
				{
					thread.notify();
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
		synchronized (thread)
		{
			try
			{
				thread.wait();
			}
			catch (InterruptedException ignored)
			{
			}
		}
	}

	public Context prepareWindow()
	{
		Pane region = new Pane();
		region.setMaxSize(1600d, 900d);
		region.setMinSize(1600d, 900d);
		Group group = new Group();
		region.getChildren().add(group);
		Context context = new Context(group);
		return context;
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
}
