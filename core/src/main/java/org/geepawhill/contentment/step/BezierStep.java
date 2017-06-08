package org.geepawhill.contentment.step;

import java.util.Random;

import org.geepawhill.contentment.core.Animator;
import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.OnFinished;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.geometry.Bezier;
import org.geepawhill.contentment.geometry.BezierSplit;
import org.geepawhill.contentment.geometry.Point;
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
	private PointPair points;
	private final Path path;
	private Format format;
	private Random random;
	Bezier bezier;
	private BezierSplit split;

	public BezierStep(Timing timing, PointPair points, Format format)
	{
		this.timing = timing;
		this.points = points;
		this.path = new Path();
		this.format = format;
		this.random = new Random();
	}

	public void setPoints(PointPair points)
	{
		this.points = points;
		format.apply(Frames.KEY, path);
		Point[] bpoints = chooseControlPoints();
		bezier = new Bezier(bpoints[0],bpoints[1],bpoints[2],bpoints[3]);
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
