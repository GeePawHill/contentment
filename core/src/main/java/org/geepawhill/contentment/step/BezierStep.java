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
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Shape;

public class BezierStep implements ShapeStep
{
	private Timing timing;
	private final Path path;
	private Format format;
	Bezier bezier;
	private BezierSplit split;

	public BezierStep(Timing timing, PointPair points, Format format)
	{
		this.timing = timing;
		this.bezier = new Bezier(points);
		this.path = new Path();
		this.format = format;
	}
	
	public void setBezier(Bezier bezier)
	{
		format.apply(Frames.KEY, path);
		this.bezier = bezier;
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
		path.setVisible(fraction != 0d);
		split = new BezierSplit(fraction,split.bezier);
		path.getElements().clear();
		path.getElements().add(new MoveTo(split.before.start.x,split.before.start.y));
		path.getElements().add(new CubicCurveTo(
				split.before.handle1.x, split.before.handle1.y,
				split.before.handle2.x, split.before.handle2.y,
				split.before.end.x,split.before.end.y));
		
	}

	@Override
	public String toString()
	{
		return "Bezier: "+bezier;
	}

	public Node node()
	{
		return path;
	}
}
