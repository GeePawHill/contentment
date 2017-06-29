package org.geepawhill.contentment.step;

import java.util.ArrayList;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.OnFinished;
import org.geepawhill.contentment.fast.Fast;
import org.geepawhill.contentment.timing.Timing;
import org.geepawhill.contentment.utility.Names;

public class Phrase implements Addable
{
	private final ArrayList<Step> playables;
	private long ms;
	private String name;
	
	public Phrase()
	{
		this(Names.make(Phrase.class));
	}
	
	public Phrase(String name)
	{
		this.name = name;
		this.playables = new ArrayList<>();
		this.ms=0L;
	}

	/* (non-Javadoc)
	 * @see org.geepawhill.contentment.step.Addable#add(org.geepawhill.contentment.step.Step)
	 */
	@Override
	public Phrase add(Step Step)
	{
		playables.add(Step);
		ms+=Step.timing().ms();
		return this;
	}
	
	/* (non-Javadoc)
	 * @see org.geepawhill.contentment.step.Addable#add(org.geepawhill.contentment.fast.Fast)
	 */
	@Override
	public Phrase add(Fast fast)
	{
		playables.add(new FastStep(fast));
		return this;
	}
	
	@Override
	public Timing timing()
	{
		return Timing.ms(ms);
	}

	@Override
	public void fast(Context context)
	{
		for(Step Step : playables)
		{
			Step.fast(context);
		}
	}

	@Override
	public void undo(Context context)
	{
		for(int i= playables.size()-1; i>=0;i--)
		{
			playables.get(i).undo(context);
		}
	}
	
	@Override
	public void slow(Context context, OnFinished onFinished)
	{
		new SlowPlayer(context,onFinished,playables);
	}
	
	@Override
	public String toString()
	{
		return name;
	}
	
}
