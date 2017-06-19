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
	
	public SlowPlayer(Context context, OnFinished onFinished, ArrayList<Step> playables)
	{
		this.context = context;
		this.onFinished = onFinished;
		this.playables = playables;
		this.current = 0;
		playables.get(current).slow(context, () -> next());
	}
	
	private void next()
	{
		current+=1;
		if(current==playables.size()) onFinished.run();
		else playables.get(current).slow(context, () -> next());
	}
}