package org.geepawhill.contentment.step;

import org.geepawhill.contentment.core.Atom;
import org.geepawhill.contentment.core.AtomRunner;
import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.OnFinished;
import org.geepawhill.contentment.timing.Timing;

public class AtomStep implements Step
{
	
	private Atom atom;
	private Timing timing;

	public AtomStep(Timing timing,Atom atom)
	{
		this.timing = timing;
		this.atom = atom;
	}

	@Override
	public void slow(Context context, OnFinished onFinished)
	{
		new AtomRunner((long)timing.ms(), atom, context, onFinished).play();
	}

	@Override
	public void fast(Context context)
	{
		atom.setup(context);
		atom.partial(context, 1d);
	}
}
