package org.geepawhill.contentment;

public class Player
{
	private PlayState status;

	public Player()
	{
		status = PlayState.Before;
	}

	public PlayState status()
	{
		return status;
	}

	public void play()
	{
		status = PlayState.After;
		
	}

	public void reset()
	{
		status = PlayState.Before;
	};
}
