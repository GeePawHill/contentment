package org.geepawhill.contentment.step.styles;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.newstep.Instant;
import org.geepawhill.contentment.style.Styles.StylesMemo;

public class PopStyles implements Instant
{
	StylesMemo popped;
	
	public PopStyles()
	{
	}
	
	@Override
	public void after(Context context)
	{
		popped = context.styles.getAll();
	}

	@Override
	public void before(Context context)
	{
		context.styles.setAll(popped);
	}
}
