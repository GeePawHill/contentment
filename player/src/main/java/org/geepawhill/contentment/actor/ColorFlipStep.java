package org.geepawhill.contentment.actor;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.OnFinished;
import org.geepawhill.contentment.step.Step;
import org.geepawhill.contentment.timing.Timing;

import javafx.scene.Node;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;

public class ColorFlipStep implements Step
{
	
	private Paint old;
	private Actor actor;
	private Paint paint;
	
	public ColorFlipStep(Actor actor, Paint paint)
	{
		this.actor = actor;
		this.paint = paint;
	}

	@Override
	public void unplay(Context context)
	{
		for(Node node : actor.group().getChildren())
		{
			if(node instanceof Shape)
			{
				Shape shape = (Shape) node;
				shape.setStroke(old);
			}
			if(node instanceof Text)
			{
				Text text = (Text) node;
				text.setFill(old);
			}
		}
		
	}

	@Override
	public void after(Context context)
	{
		old = null;
		for(Node node : actor.group().getChildren())
		{
			if(node instanceof Shape)
			{
				Shape shape = (Shape) node;
				if(old==null) old = shape.getStroke();
				shape.setStroke(paint);
			}
			if(node instanceof Text)
			{
				Text text = (Text) node;
				text.setFill(paint);
			}
		}
		
	}

	@Override
	public void play(Context context, OnFinished onFinished)
	{
		after(context);
		onFinished.run();
	}

	@Override
	public Timing timing()
	{
		return Timing.INSTANT;
	}
	
}