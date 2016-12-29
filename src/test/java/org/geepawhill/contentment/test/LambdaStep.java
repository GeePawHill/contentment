package org.geepawhill.contentment.test;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.OnFinished;
import org.geepawhill.contentment.core.Step;
import org.geepawhill.contentment.core.Timing;

class LambdaStep implements Step {
	
	
	private StepAction before;
	private StepAction after;
	private StepAction play;

	@FunctionalInterface
	static public interface StepAction
	{
		void run(Context context, OnFinished onFinished);
	}
	
	public LambdaStep( StepAction before, StepAction after, StepAction play)
	{
		this.before = before;
		this.after = after;
		this.play = play;
		
	}
	@Override
	public void after(Context context)
	{
		if(after!=null) after.run(context,null);
	}

	@Override
	public void before(Context context)
	{
		if(before!=null) before.run(context, null);
	}

	@Override
	public void play(Context context, OnFinished onFinished)
	{
		if(play!=null) play.run(context, onFinished);
	}

	@Override
	public void pause(Context context)
	{
	}

	@Override
	public void resume(Context context)
	{
	}

	@Override
	public Timing timing()
	{
		return null;
	}
	
}