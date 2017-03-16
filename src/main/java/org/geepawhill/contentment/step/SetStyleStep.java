package org.geepawhill.contentment.step;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.format.Style;

public class SetStyleStep implements Instant
{
	
	private Style style;
	private Style previous;

	public SetStyleStep(Style style)
	{
		this.style = style;
	}
	
	@Override
	public void after(Context context)
	{
		previous = context.styles.set(style);
	}

	@Override
	public void before(Context context)
	{
		context.styles.set(previous);
	}
}