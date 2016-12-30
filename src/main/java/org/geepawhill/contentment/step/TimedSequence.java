package org.geepawhill.contentment.step;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.OnFinished;
import org.geepawhill.contentment.core.Step;
import org.geepawhill.contentment.jfx.JfxUtility;
import org.geepawhill.contentment.timing.FixedTiming;
import org.geepawhill.contentment.timing.Timing;

import javafx.animation.SequentialTransition;
import javafx.scene.Group;

public class TimedSequence implements Step
{
	private SubStep reset;
	private SubStep[] substeps;
	private SequentialTransition transition;
	private final Group group;
	private Timing timing;

	public TimedSequence(double ms, Group group, SubStep... substeps)
	{
		timing = new FixedTiming(ms);
		this.group = group;
		this.substeps = substeps;
		this.transition = new SequentialTransition();
	}
	
	public TimedSequence(double ms, Group group, SubStep reset,SubStep... substeps)
	{
		timing = new FixedTiming(ms);
		this.group = group;
		this.reset = reset;
		this.substeps = substeps;
		this.transition = new SequentialTransition();
	}
	
	@Override
	public Timing timing()
	{
		return timing;
	}


	@Override
	public void after(Context context)
	{
		transition.stop();
		JfxUtility.addIfNeeded(context, group);
		for (SubStep substep : substeps)
		{
			substep.interpolator.accept(1d, context);
		}
	}

	@Override
	public void before(Context context)
	{
		transition.stop();
		if(reset!=null) reset.interpolator.accept(1d, context);
		if(context.canvas.getChildren().contains(group))
		{
			context.canvas.getChildren().remove(group);
		}
	}

	@Override
	public void play(Context context, OnFinished onFinished)
	{
		JfxUtility.addIfNeeded(context, group);
		addSubsteps(context);
		transition.setOnFinished((event) -> onFinished.run());
		transition.playFromStart();
	}

	private void addSubsteps(Context context)
	{
		transition.getChildren().clear();
		double accumulatedProportions = 0;
		for (SubStep substep : substeps)
		{
			accumulatedProportions += substep.proportion;
		}
		double timescale = timing.getAbsolute() / accumulatedProportions;
		for (SubStep substep : substeps)
		{
			transition.getChildren().add(new ContextTransition(context, substep, guaranteeProportion(timescale, substep)));
		}
	}

	private double guaranteeProportion(double timescale, SubStep substep)
	{
		return Math.max(1d, timescale * substep.proportion);
	}

	@Override
	public void pause(Context context)
	{
		transition.pause();
	}

	@Override
	public void resume(Context context)
	{
		transition.play();
	}

}
