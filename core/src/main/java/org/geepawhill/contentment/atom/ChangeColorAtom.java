package org.geepawhill.contentment.atom;

import org.geepawhill.contentment.actor.Actor;
import org.geepawhill.contentment.core.Atom;
import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.utility.ColorChanger;
import org.geepawhill.contentment.utility.JfxUtility;

import javafx.scene.paint.Paint;

public class ChangeColorAtom implements Atom
{
	
	private Actor actor;
	private Paint paint;

	public ChangeColorAtom(Actor actor, Paint paint)
	{
		this.actor = actor;
		this.paint = paint;
		
	}

	@Override
	public void setup(Context context)
	{
	}

	@Override
	public void partial(Context context, double fraction)
	{
		ColorChanger colorChanger = new ColorChanger(paint);
		JfxUtility.forEachDescendant(actor.group(), colorChanger);
	}

}
