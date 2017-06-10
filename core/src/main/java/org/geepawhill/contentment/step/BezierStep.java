package org.geepawhill.contentment.step;

import org.geepawhill.contentment.core.Animator;
import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.OnFinished;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.geometry.Bezier;
import org.geepawhill.contentment.geometry.BezierSplit;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.style.Frames;
import org.geepawhill.contentment.timing.Timing;

import javafx.scene.Node;
import javafx.scene.shape.Path;
import javafx.scene.shape.Shape;

public class BezierStep implements ShapeStep
{
	private final Timing timing;
	public final Path path;
	private final Format format;
	private BezierSplit split;
	
	public BezierStep(Timing timing, Format format)
	{
		this(timing,format,new PointPair(0d,0d,0d,0d));
	}

	public BezierStep(Timing timing, Format format, PointPair points)
	{
		this.timing = timing;
		this.path = new Path();
		this.format = format;
		this.split = new BezierSplit(0d,new Bezier(points.from,points.to));
	}
	
	public void changeBezier(Bezier bezier)
	{
		format.apply(Frames.KEY, path);
		split = new BezierSplit(1d,bezier);
	}

	@Override
	public Shape shape()
	{
		return path;
	}

	@Override
	public void fast(Context context)
	{
		interpolate(context, 1d);
	}

	@Override
	public void undo(Context context)
	{
		interpolate(context,0d);
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

	private void interpolate(Context context, double fraction)
	{
		split = new BezierSplit(fraction,split.bezier);
		split.setPathOnBefore(path);
	}

	@Override
	public String toString()
	{
		return "Bezier: "+split.bezier;
	}

	public Node node()
	{
		return path;
	}
}
