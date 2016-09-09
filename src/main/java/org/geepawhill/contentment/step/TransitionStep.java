package org.geepawhill.contentment.step;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.Step;

import javafx.animation.SequentialTransition;

public class TransitionStep implements Step
{
	
	private NodeKeeper keeper;
	private ContextTransition.Interpolator[] transitions;
	SequentialTransition transition;

	public TransitionStep(NodeKeeper keeper,ContextTransition.Interpolator... transitions)
	{
		this.keeper = keeper;
		this.transitions = transitions;
		this.transition = new SequentialTransition();
	}

	@Override
	public void after(Context context) {
		transition.stop();
		keeper.addTo(context.canvas);
		for(ContextTransition.Interpolator interpolator : transitions)
		{
			interpolator.accept(1d, context);
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
		for(ContextTransition.Interpolator interpolator : transitions)
		{
			transition.getChildren().add(new ContextTransition(context, 500d, interpolator));
		}
		transition.setOnFinished(context.onFinished);
		transition.playFromStart();
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
