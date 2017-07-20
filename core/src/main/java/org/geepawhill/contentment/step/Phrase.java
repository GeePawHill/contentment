package org.geepawhill.contentment.step;

import java.util.ArrayList;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.OnFinished;
import org.geepawhill.contentment.utility.Names;

public class Phrase implements Addable
{
	private final ArrayList<Step> playables;
	private String name;
	
	public Phrase()
	{
		this(Names.make(Phrase.class));
	}
	
	public Phrase(String name)
	{
		this.name = name;
		this.playables = new ArrayList<>();
	}

	/* (non-Javadoc)
	 * @see org.geepawhill.contentment.step.Addable#add(org.geepawhill.contentment.step.Step)
	 */
	@Override
	public Phrase add(Step Step)
	{
		playables.add(Step);
		return this;
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
