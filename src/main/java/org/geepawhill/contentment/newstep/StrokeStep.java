package org.geepawhill.contentment.newstep;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.StyleId;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.model.OnFinished;
import org.geepawhill.contentment.model.Step;
import org.geepawhill.contentment.model.Timing;
import org.geepawhill.contentment.step.ContextTransition;

import javafx.animation.Transition;
import javafx.scene.shape.Line;

public class StrokeStep implements Step
{

	private final Timing timing;
	private final PointPair points;
	private final Line line;
	private Transition transition;

	public StrokeStep(Timing timing, PointPair points, Line line)
	{
		this.timing = timing;
		this.points = points;
		this.line = line;
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
		context.apply(StyleId.LineColor,line);
		context.apply(StyleId.PenWidth,line);
		context.apply(StyleId.Opacity, line);
		line.setStartX(points.from.x);
		line.setStartY(points.from.y);
		line.setEndX(points.partialX(fraction));
		line.setEndY(points.partialY(fraction));
	}
}
