package org.geepawhill.contentment.fragments;

import org.geepawhill.contentment.core.*;
import org.geepawhill.contentment.utility.ColorChanger;
import org.geepawhill.contentment.utility.JfxUtility;

import javafx.scene.paint.Paint;

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
		JfxUtility.forEachDescendant(actor.get(), colorChanger);
		return false;
	}

}
