package org.geepawhill.contentment;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Context {
	
	public final Pane canvas;
	public EventHandler<ActionEvent> onFinished;
	
	public Paint stroke = Color.GREEN;
	
	public Context(Pane canvas)
	{
		this.canvas = canvas;
	}
}
