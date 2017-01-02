package org.geepawhill.contentment.step.styles;

import java.util.HashMap;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.newstep.Instant;
import org.geepawhill.contentment.style.Style;
import org.geepawhill.contentment.style.StyleId;

public class PopStyles implements Instant
{
	
	HashMap<StyleId,Style> popped;
	
	public PopStyles()
	{
	}
	
	@Override
	public void after(Context context)
	{
		popped = context.styles.pop();
	}

	@Override
	public void before(Context context)
	{
		context.styles.push(popped);
	}
}
