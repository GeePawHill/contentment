package org.geepawhill.contentment.step;

import org.geepawhill.contentment.actor.Actor;
import org.geepawhill.contentment.core.Sequence;
import org.geepawhill.contentment.fast.ChangeColor;
import org.geepawhill.contentment.fast.Clear;
import org.geepawhill.contentment.fast.Entrance;
import org.geepawhill.contentment.fast.Exit;

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
