package org.geepawhill.contentment.step;

import org.geepawhill.contentment.core.Animator;
import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.OnFinished;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.style.Dash;
import org.geepawhill.contentment.style.Frames;
import org.geepawhill.contentment.timing.Timing;

import javafx.animation.Transition;
import javafx.scene.shape.Ellipse;

public class OvalStep implements Step
{

	private final Timing timing;
	private PointPair points;
	private final Ellipse ellipse;
	
	private static final double VMARGIN = 4d;
	private static final double HMARGIN = 20d;
	private Format format;

	public OvalStep(Timing timing, PointPair points, Ellipse ellipse, Format format)
	{
		this.timing = timing;
		this.points = points;
		this.ellipse = ellipse;
		this.format = format;
	}
	
	public void setPoints(PointPair points)
	{
		this.points = points; 
	}

	@Override
	public void fast(Context context)
	{
		interpolate(context,1d);
	}

	@Override
	public void undo(Context context)
	{
		interpolate(context,0d);
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
	
	private void interpolate(Context context,double fraction)
	{
		if(fraction==0d)
		{
			ellipse.setVisible(false);
		}
		else
			ellipse.setVisible(true);
		format.apply(Frames.KEY,ellipse);
		format.apply(Dash.KEY,ellipse);
		ellipse.setCenterX(points.centerX());
		ellipse.setCenterY(points.centerY());
		ellipse.setRadiusX((points.width()/2d)+HMARGIN * fraction);
		ellipse.setRadiusY((points.height()/2d)+VMARGIN * fraction);
	}
}
