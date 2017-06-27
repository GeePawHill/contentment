package org.geepawhill.contentment.fast;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.geometry.PointPair;

import javafx.scene.text.Text;

public class ChangeCentered implements Fast
{

	private Text text;
	private String source;
	private String oldSource;

	private PointPair points;

	public ChangeCentered(Text text, String source, PointPair points)
	{
		this.text = text;
		this.source = source;
		this.points = points;
		this.oldSource = "";
	}

	@Override
	public void fast(Context context)
	{
		oldSource = text.getText();
		text.setText(source);
		text.setX(points.centerX() - text.getBoundsInLocal().getWidth() / 2d);
		text.setY(points.centerY());
	}

	@Override
	public void undo(Context context)
	{
		text.setText(oldSource);
		oldSource = "";
	}

	@Override
	public String toString()
	{
		return "ChangeCenteredTextStep: " + source;
	}

}
