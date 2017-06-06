package org.geepawhill.contentment.step;

import org.geepawhill.contentment.core.Animator;
import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.OnFinished;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.geometry.Point;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.style.Dash;
import org.geepawhill.contentment.style.Frames;
import org.geepawhill.contentment.timing.Timing;

import javafx.animation.Transition;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

public class StrokeStep implements ShapeStep
{

	private Timing timing;
	private PointPair points;
	private final Line line;
	private Format format;

	public StrokeStep(Timing timing, PointPair points, Format format)
	{
		this.timing = timing;
		this.points = points;
		this.line = new Line();
		this.format = format;
	}

	public void setPoints(PointPair points)
	{
		this.points = points;
	}

	@Override
	public Shape shape()
	{
		return line;
	}

	@Override
	public void fast(Context context)
	{
		interpolate(context,1d);
	}

	@Override
	public void undo(Context context)
	{
		interpolate(context, 0d);
	}

	@Override
	public void slow(Context context, OnFinished onFinished)
	{
		new Animator().play(context,onFinished,timing.ms(),this::interpolate);
	}

	@Override
	public Timing timing()
	{
		return timing;
	}

	@Override
	public String toString()
	{
		return "Stroke: " + points;
	}

	private void interpolate(Context context, double fraction)
	{
		format.apply(Frames.KEY, line);
		format.apply(Dash.KEY, line);
		line.setStartX(points.from.x);
		line.setStartY(points.from.y);
		Point newEnd = points.along(fraction);
		line.setEndX(newEnd.x);
		line.setEndY(newEnd.y);
	}
}
