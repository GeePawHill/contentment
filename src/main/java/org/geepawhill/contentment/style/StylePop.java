package org.geepawhill.contentment.style;

import java.util.HashMap;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.OnFinished;
import org.geepawhill.contentment.core.Step;
import org.geepawhill.contentment.timing.FixedTiming;
import org.geepawhill.contentment.timing.Timing;

public class StylePop implements Step
{
	
	HashMap<StyleId,Style> popped;
	
	public StylePop()
	{
		popped=null;
	}
	
	@Override
	public Timing timing()
	{
		return FixedTiming.INSTANT;
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
