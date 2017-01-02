package org.geepawhill.contentment.step.styles;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.newstep.Instant;
import org.geepawhill.contentment.style.Style;

public class SetStyle implements Instant
{
	
	private Style style;
	private Style previous;

	public SetStyle(Style style)
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
