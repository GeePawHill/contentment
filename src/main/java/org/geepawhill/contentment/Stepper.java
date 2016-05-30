package org.geepawhill.contentment;

import javafx.scene.layout.Pane;

public class Stepper
{

	Sequence sequence;
	private int current;

	public Stepper()
	{
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

	public void load(Sequence sequence)
	{
		this.sequence = sequence;
		for (Step step : sequence)
		{
			step.jumpBefore();
		}
		current = 0;
	}

	public void stepForward(Pane canvas)
	{
		if (current() < size())
		{
			sequence.get(current).jumpAfter(canvas);
			current += 1;
		}
	}

	public void stepBackward()
	{
		if (current() > 0)
		{
			current -= 1;
			sequence.get(current).jumpBefore();
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
			else stepForward(canvas);
		}
		sequence.get(current).jumpBefore();
		if(skipPastLast) sequence.get(current).jumpAfter(canvas);
	}
	
	public void play()
	{
		sequence.get(current).play();
	}

	public void pause() {
		sequence.get(current).pause();
	}
	
	public void resume() {
		sequence.get(current).resume();
	}

	public void stop() {
		sequence.get(current).stop();
	}

}
