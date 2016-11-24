package org.geepawhill.contentment.core;

import org.geepawhill.contentment.style.Style;
import org.geepawhill.contentment.style.Styles;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Context {
	
	public final Group canvas;
	public Styles styles;
	
	public Context(Group canvas)
	{
		this.canvas = canvas;
		this.styles = new Styles();
		styles.set(Style.lineColor("BLACK", Color.BLACK));
		styles.set(Style.penWidth(5d));
		styles.set(Style.font(new Font("Buxton Sketch",60d)));
		styles.set(Style.opacity(.6d));
		styles.set(Style.nodash());
		styles.push();
	}
}
