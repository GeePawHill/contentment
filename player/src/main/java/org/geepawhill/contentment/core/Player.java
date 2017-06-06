package org.geepawhill.contentment.core;

import org.geepawhill.contentment.model.PlayState;
import org.geepawhill.contentment.step.CueStep;
import org.geepawhill.contentment.step.Step;

import javafx.scene.Group;

public class Player
{
	public Context context;
	Sequence sequence;
	private int current;
	private PlayState state;
	private boolean isChaining;

	public Player(Group canvas)
	{
		context = new Context(canvas);
		sequence = new Sequence();
		current = 0;
		state = PlayState.Before;
	}

	public int size()
	{
		return sequence.size();
	}

	public int current()
	{
		return current;
	}

	public PlayState state()
	{
		return state;
	}

	public void reset(Sequence sequence)
	{
		this.sequence = sequence;
		while(current!=0) backward();
	}

	public void forward()
	{
		switch (state)
		{
		default:
		case After:
			return;
		case Playing:
		case Before:
			incrementToMarkedOrLast();
		}
	}

	public void backward()
	{
		switch (state)
		{
		default:
		case After:
		case Playing:
		case Before:
			decrementToMarkedOrZero();
		}
	}

	public void seek(int target)
	{
		boolean skipPastLast = target >= size() ? true : false;
		if (target >= size()) target = size() - 1;
		if (target < 0) target = 0;
		while (target != current)
		{
			if (target < current)
				backward();
			else
				forward();
		}
		currentStep().undo(context);
		state = PlayState.Before;
		if (skipPastLast)
		{
			incrementToMarkedOrLast();
		}
	}

	public void play()
	{
		context.skipDelays(false);
		isChaining = true;
		switch (state)
		{
		default:
		case After:
		case Playing:
			return;
		case Before:
			playCurrent();
			return;
		}
	}

	public void stop()
	{
		while (current >= 0)
			backward();
	}

	public void playOne()
	{
		context.skipDelays(true);
		isChaining = false;
		switch (state)
		{
		default:
		case After:
		case Playing:
			return;
		case Before:
			playCurrent();
			return;
		}
	}

	public Step currentStep()
	{
		return sequence.get(current);
	}

	private void decrementToMarkedOrZero()
	{
		do
		{
			currentStep().undo(context);
			current-=1;
		}
		while(current()>0 && !currentIsMarked());
		if(current()<0) current=0;
		if(current()==0)
		{
			currentStep().undo(context);
		}
		state = PlayState.Before;
	}

	private void incrementToMarkedOrLast()
	{
		do
		{
			currentStep().fast(context);
			if (!currentIsLast())
			{
				current += 1;
				state = PlayState.Before;
			}
			else
			{
				currentStep().fast(context);
				state = PlayState.After;
				return;
			}
		}
		while (!currentIsMarked());
	}

	private boolean currentIsMarked()
	{
		return sequence.steps.get(current) instanceof CueStep;
	}

	private boolean currentIsLast()
	{
		return current == size() - 1;
	}

	private void playCurrent()
	{
		state = PlayState.Playing;
		currentStep().slow(context, this::onFinished);
	}

	private void onFinished()
	{
		if (currentIsLast())
		{
			state = PlayState.After;
		}
		else
		{
			if (isChaining)
			{
				current += 1;
				playCurrent();
			}
			else
			{
				current+=1;
				if(!currentIsMarked())
				{
					playCurrent();
				}
				state = PlayState.Before;
			}
		}
	}

	public void home()
	{
		seek(0);
	}

	public void end()
	{
		seek(sequence.size());
	}

	public void allButEnd()
	{
		seek(sequence.size() - 1);
	}

}
