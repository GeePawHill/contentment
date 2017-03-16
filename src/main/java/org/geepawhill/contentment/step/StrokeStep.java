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
import javafx.scene.shape.Line;

public class StrokeStep implements Step
{

	private final Timing timing;
	private PointPair points;
	private final Line line;
	private Transition transition;
	private Format format;
	
	private final static Format UNSPECIFIED = new Format("Unspecified",Frames.unspecified(),Dash.solid());

	public StrokeStep(Timing timing, PointPair points, Line line)
	{
		this(timing,points,line,UNSPECIFIED);
	}
	
	public StrokeStep(Timing timing, PointPair points, Line line, Format format)
	{
		this.timing = timing;
		this.points = points;
		this.line = line;
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
			line.setVisible(false);
		}
		else
			line.setVisible(true);
		format.apply(Frames.KEY, line);
		format.apply(Dash.KEY, line);
		line.setStartX(points.from.x);
		line.setStartY(points.from.y);
		line.setEndX(points.partialX(fraction));
		line.setEndY(points.partialY(fraction));
	}
}
