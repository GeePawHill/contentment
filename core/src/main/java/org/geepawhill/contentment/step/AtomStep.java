package org.geepawhill.contentment.step;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.Fragment;
import org.geepawhill.contentment.core.FragmentTransition;
import org.geepawhill.contentment.core.Gesture;
import org.geepawhill.contentment.core.OnFinished;
import org.geepawhill.contentment.timing.Timing;

public class AtomStep implements Gesture
{
	
	private Fragment atom;
	private Timing timing;

	public AtomStep(Timing timing,Fragment atom)
	{
		this.timing = timing;
		this.atom = atom;
	}

	@Override
	public void slow(Context context, OnFinished onFinished)
	{
		new FragmentTransition((long)timing.ms(), atom, context, onFinished).play();
	}

	@Override
	public void fast(Context context)
	{
		atom.prepare(context);
		atom.interpolate(context, 1d);
	}
	
	@Override
	public String toString()
	{
		return atom.toString();
	}
}
