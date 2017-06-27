package org.geepawhill.contentment.step;

import java.util.ArrayList;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.OnFinished;

class SlowPlayer
{
	private int current;
	private OnFinished onFinished;
	private ArrayList<Step> playables;
	private Context context;
	private String name;

	public SlowPlayer(Context context, OnFinished onFinished, ArrayList<Step> playables, String name)
	{
		this.context = context;
		this.onFinished = onFinished;
		this.playables = playables;
		this.name = name;
		this.current = 0;
		if (playables.isEmpty())
		{
//			System.out.println("Empty?");
			onFinished.run();
		}
		else
		{
//			System.out.println(name+": Started");
			Step step = playables.get(current);
			step.slow(context, () -> next());
		}
	}

	private void next()
	{
		current += 1;
		if (current == playables.size())
		{
			onFinished.run();
		}
		else
		{
			Step step = playables.get(current);
			step.slow(context, () -> next());
		}
	}
	
}