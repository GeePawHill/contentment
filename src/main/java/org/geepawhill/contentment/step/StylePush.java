package org.geepawhill.contentment.step;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.UnmarkedStep;

public class StylePush implements UnmarkedStep
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
	public void before(Context context)
	{
		if(!hasPushed) return;
		context.styles.pop();
		hasPushed=false;
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
