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

	private void onFinished(ActionEvent e)
	{
		if (current() < size() - 1)
		{
			current += 1;
			if (isChaining)
			{
				sequence.get(current).play(context);
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
			if (current() < size() - 1)
			{
				sequence.get(current).after(context);
				current += 1;
				state = PlayState.Before;
				return;
			}
			if (current() < size())
			{
				sequence.get(current).after(context);
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
			sequence.get(current).before(context);
			state = PlayState.Before;
			return;
		case Before:
			if (current() > 0)
			{
				current -= 1;
				sequence.get(current).before(context);
			}
		}
	}

	public void seek(Pane canvas, int index)
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
		sequence.get(current).before(context);
		if (skipPastLast)
		{
			state = PlayState.After;
			sequence.get(current).after(context);
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
			sequence.get(current).play(context);
			return;
		case Paused:
			state = PlayState.Playing;
			sequence.get(current).resume(context);
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
			sequence.get(current).resume(context);
			return;
		case Playing:
			state = PlayState.Paused;
			sequence.get(current).pause(context);
			return;
		}
	}

	public void resume()
	{
		sequence.get(current).resume(context);
	}

	public void stop()
	{
		while (current >= 0)
			backward();
	}

	public PlayState getState()
	{
		return state;
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
			sequence.get(current).play(context);
			return;
		case Paused:
			state = PlayState.Playing;
			sequence.get(current).resume(context);
			return;
		}
	}
}
