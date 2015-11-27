package org.geepawhill.contentment;

public class Player
{
	
	Sequence sequence;
	
	public Player()
	{
		sequence = new Sequence();
	}

	public int size()
	{
		return sequence.size();
	}

	public int current()
	{
		return -1;
	}

	public void load(Sequence sequence)
	{
		this.sequence = sequence;
	}

}
