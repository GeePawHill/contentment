package org.geepawhill.contentment.core;

import org.geepawhill.contentment.model.PlayState;
import org.geepawhill.contentment.rhythm.Rhythm;
import org.geepawhill.contentment.rhythm.SimpleRhythm;
import org.geepawhill.contentment.step.CueMarker;
import org.geepawhill.contentment.step.Step;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Group;

public class Player
{
	public Context context;
	Sequence sequence;
	private int current;
	private final SimpleObjectProperty<PlayState> stateProperty;
	private boolean isChaining;
	private Rhythm rhythm;

	public Player(Group canvas)
	{
		this.stateProperty = new SimpleObjectProperty<>(PlayState.Before);
		rhythm = new SimpleRhythm();
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
	
	private ObjectProperty<PlayState> stateProperty()
	{
		return stateProperty;
	}
	
	public PlayState state()
	{
		return getState();
	}

	public void reset(Sequence sequence)
	{
		this.sequence = sequence;
		while (current != 0)
			backward();
	}

	public void forward()
	{
		switch (getState())
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
		switch (getState())
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
			if (target < current) backward();
			else forward();
		}
		currentStep().undo(context);
		setState(PlayState.Before);
		if (skipPastLast)
		{
			incrementToMarkedOrLast();
		}
	}

	public void play()
	{
		context.skipDelays(false);
		isChaining = true;
		switch (getState())
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
		switch (getState())
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
			current -= 1;
		}
		while (current() > 0 && !currentIsMarked());
		if (current() < 0) current = 0;
		if (current() == 0)
		{
			currentStep().undo(context);
		}
		setState(PlayState.Before);
	}

	private void incrementToMarkedOrLast()
	{
		do
		{
			currentStep().fast(context);
			if (!currentIsLast())
			{
				current += 1;
				setState(PlayState.Before);
			}
			else
			{
				currentStep().fast(context);
				setState(PlayState.After);
				return;
			}
		}
		while (!currentIsMarked());
	}

	private boolean currentIsMarked()
	{
		return sequence.steps.get(current) instanceof CueMarker;
	}

	private boolean currentIsLast()
	{
		return current == size() - 1;
	}

	private void playCurrent()
	{
		setState(PlayState.Playing);
		if(!rhythm.isPlaying()) rhythm.play();
		currentStep().slow(context, this::onFinished);
	}

	private void onFinished()
	{
		if (currentIsLast())
		{
			setState(PlayState.After);
			rhythm.pause();
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
				current += 1;
				if (!currentIsMarked())
				{
					playCurrent();
				}
				else
				{
					setState(PlayState.Before);
				}
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

	public boolean isPlaying()
	{
		return rhythm.isPlaying();
	}

	public PlayState getState()
	{
		return this.stateProperty.get();
	}

	public void setState(PlayState state)
	{
		this.stateProperty.set(state);
	}

}
