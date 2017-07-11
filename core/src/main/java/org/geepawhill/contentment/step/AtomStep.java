package org.geepawhill.contentment.step;

import org.geepawhill.contentment.core.Atom;
import org.geepawhill.contentment.core.AtomRunner;
import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.OnFinished;
import org.geepawhill.contentment.timing.Timing;

public class AtomStep implements Step
{
	
	private long ms;
	private Atom atom;

	public AtomStep(long ms,Atom atom)
	{
		this.ms = ms;
		this.atom = atom;
	}

	@Override
	public void slow(Context context, OnFinished onFinished)
	{
		new AtomRunner(ms, atom, context, onFinished).play();
	}

	@Override
	public void fast(Context context)
	{
		new AtomRunner(0, atom, context, null).play();
	}

	@Override
	public void undo(Context context)
	{
	}

	@Override
	public Timing timing()
	{
		return Timing.ms(ms);
	}

}
