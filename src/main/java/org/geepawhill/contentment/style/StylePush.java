package org.geepawhill.contentment.style;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.OnFinished;
import org.geepawhill.contentment.core.Step;
import org.geepawhill.contentment.timing.FixedTiming;
import org.geepawhill.contentment.timing.Timing;

public class StylePush implements Step
{
	boolean hasPushed;
	
	public StylePush()
	{
		hasPushed=false;
	}

	@Override
	public void after(Context context)
	{
		if(hasPushed) return;
		context.styles.push();
		hasPushed=true;
	}
	
	@Override
	public Timing timing()
	{
		return FixedTiming.INSTANT;
	}

	@Override
	public void before(Context context)
	{
		if(!hasPushed) return;
		context.styles.pop();
		hasPushed=false;
	}

	@Override
	public void play(Context context, OnFinished onFinished)
	{
		after(context);
		onFinished.run();
	}

	@Override
	public void pause(Context context)
	{
	}

	@Override
	public void resume(Context context)
	{
	}

}
