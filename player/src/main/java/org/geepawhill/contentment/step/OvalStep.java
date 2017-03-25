package org.geepawhill.contentment.step;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.model.OnFinished;
import org.geepawhill.contentment.model.Step;
import org.geepawhill.contentment.model.Timing;
import org.geepawhill.contentment.style.Dash;
import org.geepawhill.contentment.style.Frames;

import javafx.animation.Transition;
import javafx.scene.shape.Ellipse;

public class OvalStep implements Step
{

	private final Timing timing;
	private PointPair points;
	private final Ellipse ellipse;
	private Transition transition;
	
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
	public void after(Context context)
	{
		interpolate(1d,context);
	}

	@Override
	public void before(Context context)
	{
		interpolate(0d,context);
	}

	@Override
	public void play(Context context, OnFinished onFinished)
	{
		transition = new ContextTransition( context,this::interpolate,timing().getAbsolute());
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