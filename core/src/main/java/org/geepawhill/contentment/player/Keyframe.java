package org.geepawhill.contentment.player;

import org.geepawhill.contentment.step.Step;

public class Keyframe
{
	public final long target;
	public final Step phrase;
	
	public Keyframe(Step phrase)
	{
		this(0,phrase);
	}

	public Keyframe(long target, Step phrase)
	{
		this.target = target*1000;
		this.phrase = phrase;
	}
}
