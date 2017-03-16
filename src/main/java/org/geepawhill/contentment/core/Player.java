package org.geepawhill.contentment.core;

import org.geepawhill.contentment.model.PlayState;
import org.geepawhill.contentment.model.Step;
import org.geepawhill.contentment.outline.KvOutline;

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

	public Step currentStep()
	{
		return sequence.get(current);
	}

	private void decrementToMarkedOrZero()
	{
		do
		{
			currentStep().before(context);
			current-=1;
		}
		while(current()>0 && !currentIsMarked());
		if(current()<0) current=0;
		if(current()==0)
		{
			currentStep().before(context);
		}
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
			{
				currentStep().after(context);
				state = PlayState.After;
				return;
			}
		}
		while (!currentIsMarked());
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
		currentStep().play(context, this::onFinished);
	}

	private void pauseCurrent()
	{
		state = PlayState.Paused;
		currentStep().pause(context);
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

	public void dump(KvOutline dump)
	{
		dump.append("Player");
		dump.indent();
		dump.append("State",state.name());
		dump.append("Current",Integer.toString(current));
		dump.dedent();
	}

}
