package org.geepawhill.contentment;

public class Player
{

	Sequence sequence;
	private int current;

	public Player()
	{
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

	public void load(Sequence sequence)
	{
		this.sequence = sequence;
		for (Step step : sequence)
		{
			step.jumpBefore();
		}
		current = 0;
	}

	public void stepForward()
	{
		if (current() < size())
		{
			sequence.get(current).jumpAfter();
			current += 1;
		}
	}

	public void stepBackward()
	{
		if (current() > 0)
		{
			current -= 1;
			sequence.get(current).jumpBefore();
		}

	}

}
