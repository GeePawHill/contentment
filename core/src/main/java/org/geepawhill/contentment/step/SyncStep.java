package org.geepawhill.contentment.step;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.OnFinished;
import org.geepawhill.contentment.timing.Timing;

public class SyncStep implements Step, Sync
{
	private long target;
	private final Phrase phrase;
	
	public SyncStep(Phrase phrase)
	{
		this(0,phrase);
	}

	public SyncStep(long target, Phrase phrase)
	{
		this.target = target;
		this.phrase = phrase;
	}
	
	@Override
	public void slow(Context context, OnFinished onFinished)
	{
		phrase.slow(context, onFinished);
	}

	@Override
	public void fast(Context context)
	{
		phrase.fast(context);
	}

	@Override
	public void undo(Context context)
	{
		phrase.undo(context);
	}

	@Override
	public Timing timing()
	{
		return phrase.timing();
	}

	@Override
	public long target()
	{
		return target;
	}

}
