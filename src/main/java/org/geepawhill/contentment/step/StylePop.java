package org.geepawhill.contentment.step;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.UnmarkedStep;

public class StylePop implements UnmarkedStep
{
	
	boolean hasPopped;
	
	public StylePop()
	{
		hasPopped=false;
	}

	@Override
	public void after(Context context)
	{
		if(hasPopped) return;
		context.styles.pop();
		hasPopped=true;
	}

	@Override
	public void before(Context context)
	{
		if(!hasPopped) return;
		context.styles.push();
		hasPopped=false;
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
