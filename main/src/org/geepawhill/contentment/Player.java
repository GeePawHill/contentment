package org.geepawhill.contentment;

import java.util.Iterator;

import javafx.scene.layout.Pane;

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

	public void play(Pane pane)
	{
		cursor = actions.iterator();
		continuePlayUntilDone(pane);
	}

	private void continuePlayUntilDone(Pane pane)
	{
		if (cursor.hasNext())
		{
			status=PlayState.Playing;
			current = cursor.next();
			current.play(pane, event -> continuePlayUntilDone(pane));
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
	}

	public void pause()
	{
		if(status==PlayState.Playing)
		{
			current.pause();
			status = PlayState.Paused;
		}
		
	};
}
