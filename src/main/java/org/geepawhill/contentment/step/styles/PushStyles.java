package org.geepawhill.contentment.step.styles;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.newstep.Instant;

public class PushStyles implements Instant
{
	@Override
	public void after(Context context)
	{
		context.styles.push();
	}
	
	@Override
	public void before(Context context)
	{
		context.styles.pop();
	}
}
