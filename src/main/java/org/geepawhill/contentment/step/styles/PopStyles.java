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
		popped=null;
	}
	
	@Override
	public void after(Context context)
	{
		if(popped!=null) return;
		popped = context.styles.pop();
	}

	@Override
	public void before(Context context)
	{
		if(popped==null) return;
		context.styles.push(popped);
		popped=null;
	}
}
