package org.geepawhill.contentment;

import java.util.Iterator;

public class Player
{
	private PlayState status;
	private ActionList actions;
	private Action current;
	private Iterator<Action> cursor;

	public Player()
	{
		status = PlayState.Before;
		actions = new ActionList();
	}

	public PlayState status()
	{
		return status;
	}

	public void play()
	{
		cursor = actions.iterator();
		continuePlayUntilDone();
	}

	private void continuePlayUntilDone()
	{
		if (cursor.hasNext())
		{
			status=PlayState.Playing;
			current = cursor.next();
			current.play(null, event -> continuePlayUntilDone());
		}
		else
		{
			status=PlayState.After;
		}
	}

	public void reset()
	{
		status = PlayState.Before;
	}

	public void reset(ActionList actions)
	{
		this.actions = actions;
		reset();
	};
}
