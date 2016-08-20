package org.geepawhill.contentment.step;

import java.util.HashMap;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.Style;
import org.geepawhill.contentment.core.StyleId;
import org.geepawhill.contentment.core.UnmarkedStep;

public class StylePop implements UnmarkedStep
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
		context.onFinished.handle(null);
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
