package org.geepawhill.contentment.step;

import java.util.Random;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.geometry.Point;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.jfx.BezierInterpolator;
import org.geepawhill.contentment.model.OnFinished;
import org.geepawhill.contentment.model.ShapeStep;
import org.geepawhill.contentment.model.Timing;
import org.geepawhill.contentment.style.Dash;
import org.geepawhill.contentment.style.Frames;

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
		interpolator.clear(points, points.northwest());
		int count = (int) (points.distance() * .2d);
		interpolator.addCurve(chooseControlPoints(), count);
	}

	@Override
	public Shape shape()
	{
		return path;
	}

	@Override
	public void after(Context context)
	{
		interpolate(1d, context);
	}

	@Override
	public void before(Context context)
	{
		interpolate(0d, context);
	}

	@Override
	public void play(Context context, OnFinished onFinished)
	{
		transition = new ContextTransition(context, this::interpolate, timing().getAbsolute());
		transition.setOnFinished((event) -> onFinished.run());
		transition.play();
	}

	@Override
	public void pause(Context context)
	{
		transition.pause();
	}

	@Override
	public void resume(Context context)
	{
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
				points.from, points.partial(random.nextDouble()).jiggle(random, 1d, 10),
				points.partial(random.nextDouble()).jiggle(random, 1d, 10), points.to
		};
		return result;
	}

	public Node node()
	{
		return path;
	}
}