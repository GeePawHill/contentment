package org.geepawhill.contentment;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;

public class Context {
	
	public final Pane canvas;
	public EventHandler<ActionEvent> onFinished;
	
	public Context(Pane canvas)
	{
		this.canvas = canvas;
	}
}
