package org.geepawhill.contentment.newstep;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.Style;

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
		previous = context.styles.get(style.id);
		context.styles.set(style);
	}

	@Override
	public void before(Context context)
	{
		context.styles.set(previous);
	}
}
