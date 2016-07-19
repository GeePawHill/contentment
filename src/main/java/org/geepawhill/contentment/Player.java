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
			if (!currentIsLast())
			{
				current += 1;
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
			return;
		case Before:
			if (current() > 0)
			{
				current -= 1;
				beforeCurrent();
			}
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
		beforeCurrent();
		if (skipPastLast)
		{
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

	private void beforeCurrent()
	{
		currentStep().before(context);
		state = PlayState.Before;
	}

	private void afterCurrent()
	{
		currentStep().after(context);
		if (currentIsLast())
			state = PlayState.After;
		else
			state = PlayState.Before;
	}

	private boolean currentIsLast()
	{
		return current == size() - 1;
	}

	private void resumeCurrent()
	{
		currentStep().resume(context);
		state = PlayState.Playing;
	}

	private void playCurrent()
	{
		currentStep().play(context);
		state = PlayState.Playing;
	}

	private void pauseCurrent()
	{
		currentStep().pause(context);
		state = PlayState.Paused;
	}

	private void onFinished(ActionEvent e)
	{
		if (currentIsLast())
		{
			state = PlayState.After;
		}
		else
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
	}

}
