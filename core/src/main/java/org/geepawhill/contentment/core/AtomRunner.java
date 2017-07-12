package org.geepawhill.contentment.core;

import javafx.animation.Transition;
import javafx.util.Duration;

public class AtomRunner extends Transition
{

	private long ms;
	private Atom atom;
	private Context context;
	private OnFinished onFinished;

	public AtomRunner(long ms, Atom atom, Context context, OnFinished onFinished)
	{
		this.ms = ms;
		this.atom = atom;
		this.context = context;
		this.onFinished = onFinished;
	}
	
	public void play()
	{
		if (ms == 0) ms = 1;
		setCycleDuration(Duration.millis(ms));
		setOnFinished(onFinished != null ? (event) -> onFinished.run() : null);
		atom.setup(context);
		super.play();
	}

	@Override
	protected void interpolate(double fraction)
	{
		atom.partial(context, fraction);
	}

}
