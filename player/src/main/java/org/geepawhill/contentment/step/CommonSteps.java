package org.geepawhill.contentment.step;

import org.geepawhill.contentment.core.Sequence;
import org.geepawhill.contentment.model.Actor;

public class CommonSteps
{

	private Sequence sequence;

	public CommonSteps(Sequence sequence)
	{
		this.sequence = sequence;
	}

	public void clear()
	{
		sequence.add(new ClearStep());
	}

	public void hide(Actor actor)
	{
		sequence.add(new HideStep(actor.group()));
	}

	public void show(Actor actor)
	{
		sequence.add(new ShowStep(actor.group()));
	}

	public void stop()
	{
		sequence.add(new StopStep());
	}

	public void delay(double d)
	{
//		sequence.add(new TransitionStep(new ContextTransition(context, interpolator, ms)));
		
	}

}
