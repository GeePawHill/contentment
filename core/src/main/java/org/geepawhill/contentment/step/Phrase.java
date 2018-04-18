package org.geepawhill.contentment.step;

import java.util.ArrayList;

import org.geepawhill.contentment.core.*;
import org.geepawhill.contentment.utility.Names;

public class Phrase implements Addable
{
	protected final ArrayList<Gesture> gestures;
	private String name;
	
	public Phrase()
	{
		this(Names.make(Phrase.class));
	}
	
	public Phrase(String name)
	{
		this.name = name;
		this.gestures = new ArrayList<>();
	}

	/* (non-Javadoc)
	 * @see org.geepawhill.contentment.step.Addable#add(org.geepawhill.contentment.step.Step)
	 */
	@Override
	public Phrase add(Gesture Step)
	{
		gestures.add(Step);
		return this;
	}
	
	@Override
	public void fast(Context context)
	{
		for(Gesture step : gestures)
		{
			step.fast(context);
		}
	}
	
	@Override
	public void dump()
	{
		for(Gesture step : gestures)
		{
			System.out.println(step);
		}
	}


	@Override
	public void slow(Context context, OnFinished onFinished)
	{
		new SlowPlayer(context,onFinished,gestures);
	}
	
	@Override
	public String toString()
	{
		return name;
	}
	
}
