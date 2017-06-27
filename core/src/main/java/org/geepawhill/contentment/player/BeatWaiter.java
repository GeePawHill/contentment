package org.geepawhill.contentment.player;

import java.util.function.Supplier;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.OnFinished;
import org.geepawhill.contentment.step.Step;

import javafx.animation.Transition;
import javafx.util.Duration;

public class BeatWaiter
{
	private Context context;
	private Step step;
	private OnFinished onFinished;
	private Transition animator;
	private Supplier<Boolean> isDone;

	public BeatWaiter(Context context, Step step, Supplier<Boolean> isDone, OnFinished onFinished)
	{
		this.context = context;
		this.step = step;
		this.isDone = isDone;
		this.onFinished = onFinished;
	}

	public void play()
	{
		step.slow(context, this::stepFinished);
	}

	public void stepFinished()
	{
		animator = new Transition()
		{
			{
				setCycleDuration(Duration.INDEFINITE);
			}

			@Override
			protected void interpolate(double frac)
			{
				if (isDone.get())
				{
					finishAndDie();
				}
			}

		};
		animator.play();
	}

	private void finishAndDie()
	{
		animator.stop();
		onFinished.run();
	}
}