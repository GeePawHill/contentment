package org.geepawhill.contentment.style;

import java.util.HashMap;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.Step;

public class StylePop implements Step
{
	
	HashMap<StyleId,Style> popped;
	
	public StylePop()
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

	@Override
	public void play(Context context)
	{
		after(context);
		context.onFinished.run();
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
