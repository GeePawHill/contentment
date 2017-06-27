package org.geepawhill.contentment.player;

import org.geepawhill.contentment.step.Phrase;

public class Keyframe
{
	public final long target;
	public final Phrase phrase;
	
	public Keyframe(Phrase phrase)
	{
		this(0,phrase);
	}

	public Keyframe(long target, Phrase phrase)
	{
		this.target = target;
		this.phrase = phrase;
	}
}
