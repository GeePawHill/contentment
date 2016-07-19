package org.geepawhill.contentment;

import javafx.event.ActionEvent;
import javafx.scene.layout.Pane;

public class Player
{
	Context context;
	Sequence sequence;
	private int current;
	private PlayState state;
	private boolean isChaining;

	public Player(Pane canvas)
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
		for (Step step : sequence)
		{
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
			afterCurrent();
			if (current() < size() - 1)
			{
				current += 1;
				state = PlayState.Before;
				return;
			}
			else
			{
				state = PlayState.After;
				return;
			}
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
			beforeCurrent();
			state = PlayState.Before;
			return;
		case Before:
			if (current() > 0)
			{
				current -= 1;
				beforeCurrent();
			}
		}
	}

	public void seek(int index)
	{
		boolean skipPastLast = index >= size() ? true : false;
		if (index >= size()) index = size() - 1;
		if (index < 0) index = 0;
		while (index != current)
		{
			if (index < current)
				backward();
			else
				forward();
		}
		beforeCurrent();
		if (skipPastLast)
		{
			state = PlayState.After;
			afterCurrent();
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
			state = PlayState.Playing;
			playCurrent();
			return;
		case Paused:
			state = PlayState.Playing;
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
			state = PlayState.Playing;
			resumeCurrent();
			return;
		case Playing:
			state = PlayState.Paused;
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
			state = PlayState.Playing;
			playCurrent();
			return;
		case Paused:
			state = PlayState.Playing;
			resumeCurrent();
			return;
		}
	}
	
	private Step currentStep()
	{
		return sequence.get(current);
	}
	
	private void beforeCurrent()
	{
		currentStep().before(context);
	}

	private void afterCurrent()
	{
		currentStep().after(context);
	}

	private void resumeCurrent()
	{
		currentStep().resume(context);
	}

	private void playCurrent()
	{
		currentStep().play(context);
	}
	
	private void pauseCurrent()
	{
		currentStep().pause(context);
	}


	
	private void onFinished(ActionEvent e)
	{
		if (current() < size() - 1)
		{
			current += 1;
			if (isChaining)
			{
				playCurrent();
			}
			else
			{
				state = PlayState.Before;
			}
		}
		else
		{
			state = PlayState.After;
		}
	}

}
