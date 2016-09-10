package org.geepawhill.contentment.step;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.Step;

import javafx.animation.SequentialTransition;
import javafx.scene.Group;

public class TimedSequence implements Step
{
	private SubStep[] substeps;
	private SequentialTransition transition;
	private double ms;
	private final Group group;

	public TimedSequence(double ms, Group group, SubStep... substeps)
	{
		this.ms = ms;
		this.group = group;
		this.substeps = substeps;
		this.transition = new SequentialTransition();
	}

	@Override
	public void after(Context context)
	{
		transition.stop();
		context.canvas.getChildren().add(group);
		for (SubStep substep : substeps)
		{
			substep.interpolator.accept(1d, context);
		}
	}

	@Override
	public void before(Context context)
	{
		transition.stop();
		if(context.canvas.getChildren().contains(group))
		{
			context.canvas.getChildren().remove(group);
		}
	}

	@Override
	public void play(Context context)
	{
		context.canvas.getChildren().add(group);
		addSubsteps(context);
		transition.setOnFinished(context.onFinished);
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
		double timescale = ms / accumulatedProportions;
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
