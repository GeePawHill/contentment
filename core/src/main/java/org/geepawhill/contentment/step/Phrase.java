package org.geepawhill.contentment.step;

import java.util.ArrayList;

import org.geepawhill.contentment.core.*;

public class Phrase implements Addable
{
	protected final ArrayList<Gesture> gestures;
	private int current;
	private OnFinished onFinished;
	private Context context;

	public Phrase()
	{
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
		this.context = context;
		this.onFinished = onFinished;
		this.current = 0;
		if (gestures.isEmpty())
		{
			onFinished.run();
		}
		else
		{
			Gesture step = gestures.get(current);
			step.slow(context, () -> next());
		}
	}

	private void next()
	{
		current += 1;
		if (current == gestures.size())
		{
			onFinished.run();
		}
		else
		{
			Gesture step = gestures.get(current);
			step.slow(context, () -> next());
		}
	}

}
