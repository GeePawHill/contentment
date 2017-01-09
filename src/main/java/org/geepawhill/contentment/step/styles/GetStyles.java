package org.geepawhill.contentment.step.styles;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.StylesMemo;
import org.geepawhill.contentment.newstep.Instant;

public class GetStyles implements Instant
{
	
	private StylesMemo previous;
	
	@Override
	public void after(Context context)
	{
		previous = context.styles.getAll();
	}
	
	@Override
	public void before(Context context)
	{
		context.styles.setAll(previous);
	}
}
