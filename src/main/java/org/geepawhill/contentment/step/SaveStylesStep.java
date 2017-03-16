package org.geepawhill.contentment.step;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.format.StylesMemo;

public class SaveStylesStep implements Instant
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
