package org.geepawhill.contentment.step;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.OnFinished;
import org.geepawhill.contentment.timing.Timing;

import javafx.geometry.VPos;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class ChangeTitleStep implements Step
{
	
	private Text text;
	private String source;
	private String oldSource;
	
	private static final double YINSET = 20d;
	
	public ChangeTitleStep(Text text, String source)
	{
		this.text = text;
		this.source = source;
		this.oldSource ="";
	}

	@Override
	public void slow(Context context, OnFinished onFinished)
	{
		fast(context);
		onFinished.run();
	}

	@Override
	public void fast(Context context)
	{
		oldSource =text.getText();
		text.setText(source);
		Color color = Color.color(.9d, .9d, .9d);
		text.setFill(color);
		text.setStroke(color);
		text.setTextOrigin(VPos.TOP);
		text.setTextAlignment(TextAlignment.CENTER);
		text.setFont(new Font("Tahoma", 40d));
		text.setTextOrigin(VPos.TOP);
		text.setX(800d - text.getBoundsInLocal().getWidth() / 2d);
		text.setY(30d + YINSET);
		text.setStrokeWidth(3d);
		text.setStrokeLineCap(StrokeLineCap.ROUND);
	}

	@Override
	public void undo(Context context)
	{
		text.setText(oldSource);
		oldSource = "";
	}

	@Override
	public Timing timing()
	{
		return Timing.INSTANT;
	}
	
	@Override
	public String toString()
	{
		return "ChangeTitle: "+source + " from: "+oldSource;
	}

}