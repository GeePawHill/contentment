package org.geepawhill.contentment;

import javafx.scene.layout.Pane;

public class Player {
	
	Context context;
	Sequence sequence;
	private int current;
	
	public Player(Pane canvas)
	{
		context = new Context(canvas);
		sequence = new Sequence();
		current = 0;
	}

	public int size()
	{
		return sequence.size();
	}

	public int current()
	{
		return current;
	}

	public void reset(Sequence sequence)
	{
		this.sequence = sequence;
		for (Step step : sequence)
		{
			step.before(context);
		}
		current = 0;
	}

	public void stepForward()
	{
		if (current() < size())
		{
			sequence.get(current).after(context);
			current += 1;
		}
	}

	public void stepBackward()
	{
		if (current() > 0)
		{
			current -= 1;
			sequence.get(current).before(context);
		}
	}

	public void seek(Pane canvas, int index)
	{
		boolean skipPastLast = index>=size() ? true : false;
		if(index>=size()) index=size()-1;
		if(index<0) index=0;
		while(index!=current)
		{
			if(index<current) stepBackward();
			else stepForward();
		}
		sequence.get(current).before(context);
		if(skipPastLast) sequence.get(current).after(context);
	}
	
	public void play()
	{
		sequence.get(current).play(context);
	}

	public void pause() {
		sequence.get(current).pause(context);
	}
	
	public void resume() {
		sequence.get(current).resume(context);
	}

	public void stop() {
		while(current>=0) stepBackward();
	}
}
