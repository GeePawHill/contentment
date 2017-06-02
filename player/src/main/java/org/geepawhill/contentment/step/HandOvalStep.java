package org.geepawhill.contentment.step;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.OnFinished;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.geometry.Jiggler;
import org.geepawhill.contentment.geometry.Point;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.jfx.BezierInterpolator;
import org.geepawhill.contentment.model.ShapeStep;
import org.geepawhill.contentment.style.Dash;
import org.geepawhill.contentment.style.Frames;
import org.geepawhill.contentment.timing.Timing;

import javafx.animation.Transition;
import javafx.scene.Node;
import javafx.scene.shape.Path;
import javafx.scene.shape.Shape;

public class HandOvalStep implements ShapeStep
{
	private Timing timing;
	private PointPair points;
	private final Path path;
	private Transition transition;
	private Format format;
	private BezierInterpolator interpolator;
	private Jiggler controlJiggler;
	private Jiggler northJiggler;

	public HandOvalStep(Timing timing, Format format)
	{
		this.timing = timing;
		this.points = null;
		this.path = new Path();
		this.format = format;
		this.interpolator = new BezierInterpolator(path);
	}

	public void setPoints(PointPair points)
	{
		this.points = points;
		format.apply(Frames.KEY, path);
		format.apply(Dash.KEY, path);
		interpolator.clear(points.north());
		northJiggler = new Jiggler(.5d, 6d);
		controlJiggler = new Jiggler(.4d, 30d);
		int count = (int) (points.distance() * .5d);
		Jiggler jiggler = new Jiggler();//(.1d, 3d);
		interpolator.addCurve(eastHalfPoints(), count, jiggler);
		interpolator.addCurve(westHalfPoints(), count, jiggler);
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
		transition = new ContextTransition(context, this::interpolate, timing().fixed());
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

	public Point[] eastHalfPoints()
	{
		Point[] result = new Point[]
		{
				points.north(), 
				controlJiggler.jiggle(points.northeast()),
				controlJiggler.jiggle(points.southeast()), 
				points.south(),
		};
		return result;
	}

	public Point[] westHalfPoints()
	{
		Point[] result = new Point[]
		{
				points.south(), 
				controlJiggler.jiggle(points.southwest()),
				controlJiggler.jiggle(points.northwest()), 
				northJiggler.jiggle(points.north()),
		};
		return result;
	}

	public Node node()
	{
		return path;
	}
	
	public String toString()
	{
		return "HandOval: "+points;
	}
}
