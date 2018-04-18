package org.geepawhill.contentment.step;

import java.util.ArrayList;

import org.geepawhill.contentment.core.*;
import org.geepawhill.contentment.utility.Names;

public class Phrase implements Addable
{
	
	private SlowPlayer player;
	protected final ArrayList<Gesture> gestures;
	
	public static Phrase phrase()
	{
		return new Phrase(new SequencePlayer());
	}
	
	public static Phrase chord()
	{
		return new Phrase(new ChordPlayer());
	}
	
	public Phrase(SlowPlayer player)
	{
		this.player = player;
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
		player.play(context,onFinished,gestures);
	}	
}
