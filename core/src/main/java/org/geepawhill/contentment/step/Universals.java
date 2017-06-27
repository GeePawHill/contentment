package org.geepawhill.contentment.step;

import org.geepawhill.contentment.actor.Actor;
import org.geepawhill.contentment.fast.ChangeColor;
import org.geepawhill.contentment.fast.Clear;
import org.geepawhill.contentment.fast.Entrance;
import org.geepawhill.contentment.fast.Exit;

import javafx.scene.paint.Paint;

public class Universals
{

	static public Step clear()
	{
		return new FastStep(new Clear());
	}

	static public Step cue()
	{
		return new CueStep();
	}
	
	static public Step reColor(Actor actor, Paint paint)
	{
		return new FastStep(new ChangeColor(actor, paint));
	}

	static public Step sketch(double ms, Actor drawable)
	{
		Phrase phrase = new Phrase();
		phrase.add(new Entrance(drawable));
		phrase.add(drawable.draw(ms));
		return phrase;
	}

	static public Step appear(Actor drawable)
	{
		Phrase result = new Phrase();
		result.add(new Entrance(drawable));
		result.add(drawable.draw(1d));
		return result;
	}

	static public Step disappear(Actor drawable)
	{
		return new FastStep(new Exit(drawable));
	}

	static public Step fadeIn(double ms, Actor drawable)
	{
		Phrase result = new Phrase();
		result.add(new Entrance(drawable));
		result.add(new OpacityStep(0d, drawable, 0d));
		result.add(drawable.draw(1d));
		result.add(new OpacityStep(ms, drawable, 1d));
		return result;
	}

}
