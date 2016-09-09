package org.geepawhill.contentment.step;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.Step;

import javafx.animation.SequentialTransition;

public class InterpolatedStep implements Step
{

	private NodeKeeper keeper;
	private SubStep[] substeps;
	private SequentialTransition transition;
	private double ms;

	public InterpolatedStep(NodeKeeper keeper, double ms, SubStep... substeps)
	{
		this.keeper = keeper;
		this.ms = ms;
		this.substeps = substeps;
		this.transition = new SequentialTransition();
	}

	@Override
	public void after(Context context)
	{
		transition.stop();
		keeper.addTo(context.canvas);
		for (SubStep substep : substeps)
		{
			substep.interpolator.accept(1d, context);
		}
	}

	@Override
	public void before(Context context)
	{
		transition.stop();
		keeper.removeFrom(context.canvas);
	}

	@Override
	public void play(Context context)
	{
		keeper.addTo(context.canvas);
		transition.getChildren().clear();
		addSubsteps(context);
		transition.setOnFinished(context.onFinished);
		transition.playFromStart();
	}

	private void addSubsteps(Context context)
	{
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

	@Override
	public boolean isMarked()
	{
		return true;
	}

}
