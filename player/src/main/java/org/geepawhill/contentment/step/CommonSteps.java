package org.geepawhill.contentment.step;

import org.geepawhill.contentment.actor.Actor;
import org.geepawhill.contentment.core.Sequence;

import javafx.scene.paint.Paint;

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
	
	public void keyframe(double seconds)
	{
		keyframe(0d,seconds);
	}
	
	public void keyframe(double minutes,double seconds)
	{
		double adjusted = (minutes*60d+seconds)*1000d;
		double here = sequence.runTime();
		double delay = adjusted-here;
		if(delay<0) delay = 1d;
		sequence.add(new DelayStep(delay));
	}
	
	public void reColor(Actor actor,Paint paint)
	{
		sequence.add(new ColorFlipStep(actor,paint));
	}
}
