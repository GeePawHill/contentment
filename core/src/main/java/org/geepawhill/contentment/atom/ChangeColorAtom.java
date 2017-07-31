package org.geepawhill.contentment.atom;

import org.geepawhill.contentment.core.Atom;
import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.utility.ColorChanger;
import org.geepawhill.contentment.utility.JfxUtility;

import javafx.scene.paint.Paint;

public class ChangeColorAtom implements Atom
{
	
	private GroupSource actor;
	private Paint paint;

	public ChangeColorAtom(GroupSource actor, Paint paint)
	{
		this.actor = actor;
		this.paint = paint;
		
	}

	@Override
	public void setup(Context context)
	{
	}

	@Override
	public boolean partial(Context context, double fraction)
	{
		ColorChanger colorChanger = new ColorChanger(paint);
		JfxUtility.forEachDescendant(actor.get(), colorChanger);
		return false;
	}

}
