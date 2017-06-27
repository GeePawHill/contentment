package org.geepawhill.contentment.fast;

import org.geepawhill.contentment.actor.Actor;
import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.utility.ColorChanger;
import org.geepawhill.contentment.utility.JfxUtility;

import javafx.scene.paint.Paint;

public class ChangeColor implements Fast
{

	private Actor actor;
	private Paint paint;
	private Paint old;

	public ChangeColor(Actor actor, Paint paint)
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

}