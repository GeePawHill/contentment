package org.geepawhill.contentment;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.Sequence;
import org.geepawhill.contentment.core.Step;

import javafx.event.ActionEvent;
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
		context.onFinished = this::onFinished;
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
		for(int i=0;i<sequence.size();i++)
		{
			Step step = sequence.get(i);
			step.before(context);
		}
		current = 0;
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
		case Paused:
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
		case Paused:
			currentStep().before(context);
			state = PlayState.Before;
			return;
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
		currentStep().before(context);
		state = PlayState.Before;
		if (skipPastLast)
		{
			incrementToMarkedOrLast();
		}
	}

	public void play()
	{
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
		case Paused:
			resumeCurrent();
			return;
		}
	}

	public void pause()
	{
		switch (state)
		{
		default:
		case After:
		case Before:
			return;
		case Paused:
			resumeCurrent();
			return;
		case Playing:
			pauseCurrent();
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
		case Paused:
			resumeCurrent();
			return;
		}
	}

	private Step currentStep()
	{
		return sequence.get(current);
	}

	private void decrementToMarkedOrZero()
	{
		if (current == 0)
		{
			currentStep().before(context);
			state = PlayState.Before;
			return;
		}
		do
		{
			current -= 1;
			currentStep().before(context);
		}
		while (current() > 0 && !currentIsMarked());
		state = PlayState.Before;
	}

	private void incrementToMarkedOrLast()
	{
		do
		{
			currentStep().after(context);
			if (!currentIsLast())
			{
				current += 1;
				state = PlayState.Before;
			}
			else
				state = PlayState.After;
		}
		while (!currentIsLast() && !currentIsMarked());
	}

	private boolean currentIsMarked()
	{
		return sequence.isMarked(current);
	}

	private boolean currentIsLast()
	{
		return current == size() - 1;
	}

	private void resumeCurrent()
	{
		state = PlayState.Playing;
		currentStep().resume(context);
	}

	private void playCurrent()
	{
		state = PlayState.Playing;
		currentStep().play(context);
	}

	private void pauseCurrent()
	{
		state = PlayState.Paused;
		currentStep().pause(context);
	}

	private void onFinished(ActionEvent e)
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
				if (!currentIsMarked())
				{
					current += 1;
					playCurrent();
					return;
				}
				current+=1;
				state = PlayState.Before;
			}
		}
	}

	public void home()
	{
		System.out.println("Seeking to home.");
		seek(0);
	}

	public void end()
	{
		System.out.println("Seeking to end.");
		seek(sequence.size());
	}

	public void allButEnd()
	{
		seek(sequence.size() - 1);
	}

}