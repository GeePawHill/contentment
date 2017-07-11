package org.geepawhill.contentment.core;

import javafx.animation.Transition;
import javafx.util.Duration;

public class AtomRunner extends Transition
{

	private long ms;
	private ContextInterpolator atom;
	private Context context;
	private OnFinished onFinished;

	public AtomRunner(long ms, ContextInterpolator atom, Context context, OnFinished onFinished)
	{
		this.ms = ms;
		this.atom = context.wrap(atom);
		this.context = context;
		this.onFinished = onFinished;
	}
	
	public void play()
	{
		if (ms == 0) ms = 1;
		setCycleDuration(Duration.millis(ms));
		setOnFinished(onFinished != null ? (event) -> onFinished.run() : null);
		interpolate(0d);
		super.play();
	}

	@Override
	protected void interpolate(double fraction)
	{
		atom.interpolate(context, fraction);
	}

}
