package org.geepawhill.contentment.step;

import org.geepawhill.contentment.actor.Actor;
import org.geepawhill.contentment.actor.Actors;
import org.geepawhill.contentment.fast.ChangeColor;
import org.geepawhill.contentment.fast.Clear;
import org.geepawhill.contentment.fast.Entrance;
import org.geepawhill.contentment.fast.Exit;
import org.geepawhill.contentment.fast.Fast;

import javafx.scene.paint.Paint;

public class ScriptBuilder
{
	
	Addable working=null;

	public Step clear()
	{
		FastStep step = new FastStep(new Clear());
		addToWorking(step);
		return step;
	}


	public Step reColor(Actor actor, Paint paint)
	{
		FastStep step = new FastStep(new ChangeColor(actor, paint));
		addToWorking(step);
		return step;
	}

	public Step sketch(double ms, Actor drawable)
	{
		Phrase phrase = new Phrase();
		phrase.add(new Entrance(drawable));
		phrase.add(drawable.draw(ms));
		addToWorking(phrase);
		return phrase;
	}

	public Step appear(Actor drawable)
	{
		Phrase result = new Phrase();
		result.add(new Entrance(drawable));
		result.add(drawable.draw(1d));
		addToWorking(result);
		return result;
	}

	public Step disappear(Actor drawable)
	{
		FastStep step = new FastStep(new Exit(drawable));
		addToWorking(step);
		return step;
	}

	public Step fadeIn(double ms, Actor drawable)
	{
		Phrase result = new Phrase();
		result.add(new Entrance(drawable));
		result.add(new OpacityStep(1d, drawable, 0d));
		result.add(drawable.draw(1d));
		result.add(new OpacityStep(ms, drawable, 1d));
		addToWorking(result);
		return result;
	}
	
	public Step fadeOut(double ms, Actor drawable)
	{
		Phrase result = new Phrase();
		result.add(new OpacityStep(ms, drawable, 0d));
		result.add(new Exit(drawable));
		addToWorking(result);
		return result;
	}

	
	public Step mark(long ms)
	{
		MarkStep step = new MarkStep(ms);
		addToWorking(step);
		return step;
	}
	
	public void addToWorking(Step step)
	{
		if(working!=null) working.add(step);
	}
	
	public void addToWorking(Fast step)
	{
		addToWorking(new FastStep(step));
	}
	
	public void setWorking(Phrase phrase)
	{
		working = phrase;
	}
	
	public Addable buildPhrase()
	{
		working = new Phrase();
		return working;
	}
	
	public Addable buildChord()
	{
		working = new Chord();
		return working;
	}
	
	public Addable buildMore(Addable addable)
	{
		working = addable;
		return working;
	}
	
	public Addable endBuild()
	{
		Addable temp = working;
		working=null;
		return temp;
	}
	
	public void fadeIn(double ms, Actors... arrays)
	{
		Addable temp = endBuild();

		buildChord();
		for (Actors actors : arrays)
		{
			for (Actor actor : actors)
			{
				fadeIn(ms, actor);
			}
		}
		Addable chord = endBuild();
		buildMore(temp);
		addToWorking(chord);
	}

	public void fadeOut(double ms, Actors... arrays)
	{
		Addable temp = endBuild();

		buildChord();
		for (Actors actors : arrays)
		{
			for (Actor actor : actors)
			{
				fadeOut(ms, actor);
			}
		}
		Addable chord 	= endBuild();
		buildMore(temp);
		addToWorking(chord);
	}


	public ScriptBuilder fadeDown(double ms, Actor actor)
	{
		addToWorking(new OpacityStep(ms, actor,0d));
		return this;
	}
	
	public ScriptBuilder fadeUp(double ms, Actor actor)
	{
		addToWorking(new OpacityStep(ms, actor,1d));
		return this;
	}
	
	public void fadeDown(double ms, Actors... arrays)
	{
		Addable temp = endBuild();

		buildChord();
		for (Actors actors : arrays)
		{
			for (Actor actor : actors)
			{
				fadeDown(ms, actor);
			}
		}
		Addable chord 	= endBuild();
		buildMore(temp);
		addToWorking(chord);
	}

	public void fadeUp(double ms, Actors... arrays)
	{
		Addable temp = endBuild();

		buildChord();
		for (Actors actors : arrays)
		{
			for (Actor actor : actors)
			{
				fadeUp(ms, actor);
			}
		}
		Addable chord 	= endBuild();
		buildMore(temp);
		addToWorking(chord);
	}

}
