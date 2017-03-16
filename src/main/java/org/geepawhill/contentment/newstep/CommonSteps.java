package org.geepawhill.contentment.newstep;

import org.geepawhill.contentment.core.Sequence;
import org.geepawhill.contentment.format.Style;
import org.geepawhill.contentment.model.Actor;
import org.geepawhill.contentment.step.ClearStep;
import org.geepawhill.contentment.step.HideStep;
import org.geepawhill.contentment.step.ShowStep;

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
	
	public void set(Style style)
	{
		sequence.add(new SetStyleStep(style));
	}

	
	public void stop()
	{
		sequence.add(new StopStep());
	}

	public void saveStyles()
	{
		sequence.unmarked(new SaveStylesStep());
	}

	public void restoreStyles()
	{
		sequence.unmarked(new RestoreStylesStep());
	}


}
