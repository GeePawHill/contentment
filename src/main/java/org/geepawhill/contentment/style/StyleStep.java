package org.geepawhill.contentment.style;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.FixedTiming;
import org.geepawhill.contentment.core.OnFinished;
import org.geepawhill.contentment.core.Step;
import org.geepawhill.contentment.core.Timing;

public class StyleStep implements Step
{
	
	private Style style;
	private Style previous;

	public StyleStep(Style style)
	{
		this.style = style;
		this.previous = null;
	}
	
	@Override
	public Timing timing()
	{
		return FixedTiming.INSTANT;
	}

	@Override
	public void after(Context context)
	{
		if(previous==null) previous = context.styles.get(style.id);
		context.styles.set(style);
	}

	@Override
	public void before(Context context)
	{
		if(previous!=null) context.styles.set(previous);
		previous=null;
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
