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
		sequence.add(new DelayStep(d));
	}
	
	public void mark(double seconds)
	{
		mark(0d,seconds);
	}
	
	public void mark(double minutes,double seconds)
	{
		double adjusted = (minutes*60d+seconds)*1000d;
		double here = sequence.runTime();
		double delay = adjusted-here;
		if(delay<0) delay = 1d;
		sequence.add(new DelayStep(delay));
	}

}
