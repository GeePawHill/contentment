package org.geepawhill.contentment.step;

import java.util.ArrayList;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.Gesture;
import org.geepawhill.contentment.core.OnFinished;
import org.geepawhill.contentment.utility.Names;

public class Phrase implements Addable
{
	private final ArrayList<Gesture> playables;
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
	public Phrase add(Gesture Step)
	{
		playables.add(Step);
		return this;
	}
	
	@Override
	public void fast(Context context)
	{
		for(Gesture step : playables)
		{
			step.fast(context);
		}
	}
	
	@Override
	public void dump()
	{
		for(Gesture step : playables)
		{
			System.out.println(step);
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
