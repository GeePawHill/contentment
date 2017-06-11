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
		sequence.add(new Clear());
	}

	public void cue()
	{
		sequence.add(new CueStep());
	}

	public void delay(double d)
	{
		sequence.add(new DelayStep(d));
	}

	public void keyframe(double seconds)
	{
		keyframe(0d, seconds);
	}

	public void keyframe(double minutes, double seconds)
	{
		double adjusted = (minutes * 60d + seconds) * 1000d;
		double here = sequence.runTime();
		double delay = adjusted - here;
		if (delay < 0) delay = 1d;
		sequence.add(new DelayStep(delay));
	}

	public void reColor(Actor actor, Paint paint)
	{
		sequence.add(new ChangeColor(actor, paint));
	}

	public void sketch(double ms, Actor drawable)
	{
		Sequence result = new Sequence();
		result.add(new Entrance(drawable));
		result.add(drawable.draw(ms));
		sequence.add(result);
	}

	public void appear(Actor drawable)
	{
		Sequence result = new Sequence();
		result.add(new Entrance(drawable));
		result.add(drawable.draw(1d));
		sequence.add(result);
	}

	public void disappear(Actor drawable)
	{
		sequence.add(new Exit(drawable));
	}

	public void fadeIn(double ms, Actor drawable)
	{
		Sequence result = new Sequence();
		result.add(new Entrance(drawable));
		result.add(new OpacityStep(0d, drawable, 0d));
		result.add(drawable.draw(ms));
		result.add(new OpacityStep(ms, drawable, 1d));
		sequence.add(result);
	}

}
