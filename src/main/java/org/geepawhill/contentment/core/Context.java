package org.geepawhill.contentment.core;

import org.geepawhill.contentment.style.Dash;
import org.geepawhill.contentment.style.LineColor;
import org.geepawhill.contentment.style.Opacity;
import org.geepawhill.contentment.style.PenWidth;
import org.geepawhill.contentment.style.Styles;
import org.geepawhill.contentment.tree.KeyValue;
import org.geepawhill.contentment.tree.TreeOutput;

import javafx.scene.Group;
import javafx.scene.text.Font;

public class Context {
	
	public final Group canvas;
	public Styles styles;
	
	public Context(Group canvas)
	{
		this.canvas = canvas;
		this.styles = new Styles();
		styles.set(LineColor.black());
		styles.set(PenWidth.penWidth(5d));
		styles.set(org.geepawhill.contentment.style.Font.font(new Font("Buxton Sketch",60d)));
		styles.set(Opacity.opacity(.5d));
		styles.set(Dash.solid());
		styles.push();
	}

	public void dump(TreeOutput<KeyValue> output)
	{
		this.styles.dump(output);
	}
}
