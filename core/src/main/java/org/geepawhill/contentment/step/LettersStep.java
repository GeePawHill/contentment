package org.geepawhill.contentment.step;

import org.controlsfx.tools.Platform;
import org.geepawhill.contentment.core.Animator;
import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.OnFinished;
import org.geepawhill.contentment.format.Aligner;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.geometry.Point;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.style.TypeFace;
import org.geepawhill.contentment.timing.Timing;

import javafx.geometry.Bounds;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;

public class LettersStep implements ShapeStep
{
	private final Timing timing;
	public Point center;
	private final String source;
	public final Text text;
	private Format format;
	private Aligner aligner;
	private Object oldPartialSource;

	public LettersStep(Timing timing, String source, Point center, Format format)
	{
		this(timing,source,center,format,new Aligner(HPos.CENTER,VPos.TOP));
	}

	public LettersStep(Timing timing, String source, Point center, Format format, Aligner aligner)
	{
		this.timing = timing;
		this.center = center;
		this.source = source;
		this.format = format;
		this.aligner = aligner;
		this.text = new Text(source);
		format.apply(TypeFace.FACE, text);
		format.apply(TypeFace.COLOR, text);
		double finalWidth = new PointPair(text.getBoundsInLocal()).width();
		aligner.setFinalWidth(finalWidth);
		this.text.setText(" ");
	}

	@Override
	public void fast(Context context)
	{
		interpolate(context, 1d);
	}

	@Override
	public void undo(Context context)
	{
		interpolate(context, 0d);
	}

	@Override
	public void slow(Context context, OnFinished onFinished)
	{
		new Animator().play(context, onFinished, timing.ms(), this::interpolate);
	}

	@Override
	public Timing timing()
	{
		return timing;
	}

	public void interpolate(Context context, double fraction)
	{
		if (fraction == 0d)
		{
			text.setText("");
		}
		String partialSource = source.substring(0, (int) (fraction * source.length()));
		if(partialSource.equals(oldPartialSource)) return;
		oldPartialSource=partialSource;
//		text.setTextOrigin(VPos.CENTER);
		text.setText(partialSource);
		aligner.align(center, text);
//		Bounds boundsInParent = text.getBoundsInParent();
//		Rectangle rectangle = new Rectangle(boundsInParent.getMinX(),boundsInParent.getMinY(),boundsInParent.getWidth(),boundsInParent.getHeight());
//		rectangle.setStroke(Color.RED);
//		rectangle.setFill(Color.TRANSPARENT);
//		context.canvas.getChildren().add(rectangle);
	}

	public String toString()
	{
		return "Letters: " + source;
	}

	@Override
	public Shape shape()
	{
		return text;
	}

	public void setY(double y)
	{
		center = new Point(center.x,y);
	}
}
