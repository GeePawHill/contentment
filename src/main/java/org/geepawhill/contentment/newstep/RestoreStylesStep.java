package org.geepawhill.contentment.newstep;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.format.StylesMemo;

public class RestoreStylesStep implements Instant
{
	StylesMemo previous;
	
	public RestoreStylesStep()
	{
	}
	
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
