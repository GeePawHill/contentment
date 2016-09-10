package org.geepawhill.contentment.core;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Context {
	
	public final Group canvas;
	public EventHandler<ActionEvent> onFinished;
	public Styles styles;
	
	public Context(Group canvas)
	{
		this.canvas = canvas;
		this.styles = new Styles();
		styles.set(Style.lineColor(Color.BLACK));
		styles.set(Style.penWidth(5d));
		styles.set(Style.font(new Font("Buxton Sketch",60d)));
		styles.set(Style.opacity(.6d));
		styles.set(Style.nodash());
		styles.push();
		System.out.println("Styles set up.");
	}
}
