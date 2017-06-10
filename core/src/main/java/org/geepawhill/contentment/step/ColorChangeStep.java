package org.geepawhill.contentment.step;

import org.geepawhill.contentment.actor.Actor;
import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.OnFinished;
import org.geepawhill.contentment.timing.Timing;
import org.geepawhill.contentment.utility.ColorChanger;
import org.geepawhill.contentment.utility.JfxUtility;

import javafx.scene.paint.Paint;

public class ColorChangeStep implements Step
{

	private Actor actor;
	private Paint paint;
	private Paint old;

	public ColorChangeStep(Actor actor, Paint paint)
	{
		this.actor = actor;
		this.paint = paint;
	}

	@Override
	public void undo(Context context)
	{
		changeColor(old);
	}

	@Override
	public void fast(Context context)
	{
		old = changeColor(paint);
	}

	private Paint changeColor(Paint paint)
	{
		ColorChanger colorChanger = new ColorChanger(paint);
		JfxUtility.forEachDescendant(actor.group(), colorChanger);
		return colorChanger.result;
	}

	@Override
	public void slow(Context context, OnFinished onFinished)
	{
		fast(context);
		onFinished.run();
	}

	@Override
	public Timing timing()
	{
		return Timing.instant();
	}

}