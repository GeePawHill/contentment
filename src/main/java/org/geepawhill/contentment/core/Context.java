package org.geepawhill.contentment.core;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Context {
	
	public final Pane canvas;
	public EventHandler<ActionEvent> onFinished;
	public Styles styles;
	
	public Context(Pane canvas)
	{
		this.canvas = canvas;
		this.styles = new Styles();
		styles.set(Style.lineColor(Color.BLACK));
		styles.set(Style.penWidth(6d));
	}
}
