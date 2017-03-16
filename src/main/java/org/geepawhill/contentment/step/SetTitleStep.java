package org.geepawhill.contentment.step;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.model.OnFinished;
import org.geepawhill.contentment.model.Step;
import org.geepawhill.contentment.model.Timing;
import org.geepawhill.contentment.timing.FixedTiming;

import javafx.geometry.VPos;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class SetTitleStep implements Step
{
	
	private Text text;
	private String source;
	private String previous;
	
	private final double YINSET = 20d;

	public SetTitleStep(Text text,String source)
	{
		this.text = text;
		this.source = source;
		adjustTextSize();
	}

	@Override
	public void after(Context context)
	{
		previous = text.getText();
		changeText(source);
	}

	@Override
	public void before(Context context)
	{
		changeText(previous);
	}

	@Override
	public void play(Context context, OnFinished onFinished)
	{
		after(context);
		onFinished.run();
	}

	@Override
	public void pause(Context context)
	{
	}

	@Override
	public void resume(Context context)
	{
	}

	@Override
	public Timing timing()
	{
		return FixedTiming.INSTANT;
	}
	
	private void adjustTextSize()
	{
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

	private void changeText(String newValue)
	{
		text.setText(newValue);
		adjustTextSize();
	}

}
