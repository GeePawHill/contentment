package org.geepawhill.contentment.step;

import org.geepawhill.contentment.core.Sequence;
import org.geepawhill.contentment.core.Style;
import org.geepawhill.contentment.model.Actor;
import org.geepawhill.contentment.newstep.StopStep;
import org.geepawhill.contentment.step.styles.GetStyles;
import org.geepawhill.contentment.step.styles.SetStyle;
import org.geepawhill.contentment.step.styles.SetStyles;

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
		sequence.add(new SetStyle(style));
	}

	
	public void stop()
	{
		sequence.add(new StopStep());
	}

	public void saveStyles()
	{
		sequence.unmarked(new GetStyles());
	}

	public void restoreStyles()
	{
		sequence.unmarked(new SetStyles());
	}


}
