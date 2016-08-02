package org.geepawhill.contentment;

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
		styles.set(new Style(StyleId.LineColor,Color.BLACK));
		styles.set(new Style(StyleId.PenWidth,6d));
	}
}
