package org.geepawhill.contentment.step;

import java.util.Random;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.OnFinished;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.geometry.Point;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.jfx.BezierInterpolator;
import org.geepawhill.contentment.style.Dash;
import org.geepawhill.contentment.style.Frames;
import org.geepawhill.contentment.timing.Timing;

import javafx.animation.Transition;
import javafx.scene.Node;
import javafx.scene.shape.Path;
import javafx.scene.shape.Shape;

public class HandStep implements ShapeStep
{
	private Timing timing;
	private PointPair points;
	private final Path path;
	private Transition transition;
	private Format format;
	private Random random;
	private BezierInterpolator interpolator;

	public HandStep(Timing timing, PointPair points, Format format)
	{
		this.timing = timing;
		this.points = points;
		this.path = new Path();
		this.format = format;
		this.random = new Random();
		this.interpolator = new BezierInterpolator(path);
	}

	public void setPoints(PointPair points)
	{
		this.points = points;
		format.apply(Frames.KEY, path);
		format.apply(Dash.KEY, path);
		interpolator.clear(points.northwest());
		int count = (int) (points.distance() * .2d);
		interpolator.addCurve(chooseControlPoints(), count);
	}

	@Override
	public Shape shape()
	{
		return path;
	}

	@Override
	public void fast(Context context)
	{
		interpolate(1d, context);
	}

	@Override
	public void undo(Context context)
	{
		interpolate(0d, context);
	}

	@Override
	public void slow(Context context, OnFinished onFinished)
	{
		transition = new ContextTransition(context, this::interpolate, timing().ms());
		transition.setOnFinished((event) -> onFinished.run());
		transition.play();
	}

	@Override
	public Timing timing()
	{
		return timing;
	}

	private void interpolate(double fraction, Context context)
	{
		path.setVisible(fraction != 0d);
		interpolator.interpolate(fraction);
	}

	public Point[] chooseControlPoints()
	{
		Point[] result = new Point[]
		{
				points.from, points.along(random.nextDouble()).jiggle(random, 1d, 10),
				points.along(random.nextDouble()).jiggle(random, 1d, 10), points.to
		};
		return result;
	}
	
	@Override
	public String toString()
	{
		return "Hand: "+new PointPair(points.from,points.to);
	}

	public Node node()
	{
		return path;
	}
}
