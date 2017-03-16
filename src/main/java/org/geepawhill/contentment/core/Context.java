package org.geepawhill.contentment.core;

import org.geepawhill.contentment.format.Styles;
import org.geepawhill.contentment.outline.KvOutline;
import org.geepawhill.contentment.style.Dash;
import org.geepawhill.contentment.style.Opacity;
import org.geepawhill.contentment.style.ShapePen;

import javafx.scene.Group;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;

public class Context {
	
	public final Actors actors;
	public final Group canvas;
	public Styles styles;
	
	public Context(Group canvas)
	{
		this.canvas = canvas;
		this.actors = new Actors();
		this.styles = new Styles();
		styles.set(ShapePen.first());
		styles.set(org.geepawhill.contentment.style.Typeface.font("?", new Font("Buxton Sketch",60d)));
		styles.set(Opacity.opacity(.5d));
		styles.set(Dash.solid());
	}

	public void outline(KvOutline output)
	{
		styles.outline(output);
		actors.outline(output);
	}

	public KvOutline outline()
	{
		KvOutline output = new KvOutline();
		outline(output);
		return output;
	}
	
	public void apply(String kind, Shape shape)
	{
		styles.get(kind).apply(shape);
	}
}
