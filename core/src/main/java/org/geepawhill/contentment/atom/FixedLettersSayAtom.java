package org.geepawhill.contentment.atom;

import java.util.function.Supplier;

import org.geepawhill.contentment.core.Fragment;
import org.geepawhill.contentment.core.Context;

import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public class FixedLettersSayAtom implements Fragment
{

	private Supplier<Canvas> canvas;
	private Paint paint;
	private double x;
	private double y;
	private String text;
	private Font font;

	public FixedLettersSayAtom(Supplier<Canvas> canvas, Font font, Paint paint, double x, double y, String text)
	{
		this.canvas = canvas;
		this.font = font;
		this.paint = paint;
		this.x = x;
		this.y = y;
		this.text = text;
	}

	@Override
	public void prepare(Context context)
	{
		GraphicsContext graphics = canvas.get().getGraphicsContext2D();
		graphics.setTextBaseline(VPos.TOP);
		graphics.setStroke(paint);
		graphics.setFont(font);
		graphics.setFill(paint);
		graphics.fillText(text, x, y);
	}

	@Override
	public boolean interpolate(Context context, double fraction)
	{
		return false;
	}

}
