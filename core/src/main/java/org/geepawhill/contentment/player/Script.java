package org.geepawhill.contentment.player;

import java.util.ArrayList;

public class Script
{
	ArrayList<Keyframe> steps;

	public Script()
	{
		steps = new ArrayList<>();
	}

	public int size()
	{
		return steps.size();
	}

	public Keyframe get(int index)
	{
		return steps.get(index);
	}

	public Script add(Keyframe step)
	{
		steps.add(step);
		return this;
	}
}