package org.geepawhill.contentment.fragments;

import org.geepawhill.contentment.core.*;
import org.geepawhill.contentment.utility.*;

import javafx.scene.paint.Paint;

/**
 * Changes the color of its target and all its target's children.
 * 
 * @author GeePaw
 *
 */
public class Recolor implements Fragment
{

	private GroupSource actor;
	private Paint paint;

	public Recolor(GroupSource actor, Paint paint)
	{
		this.actor = actor;
		this.paint = paint;
	}

	@Override
	public void prepare(Context context)
	{
	}

	@Override
	public boolean interpolate(Context context, double fraction)
	{
		ColorChanger colorChanger = new ColorChanger(paint);
		JfxUtility.forEachDescendant(actor.group(), colorChanger);
		return false;
	}
}
